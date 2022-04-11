package com.whoai.blog.service.impl;

import com.whoai.blog.constant.Permission;
import com.whoai.blog.constant.Role;
import com.whoai.blog.constant.Status;
import com.whoai.blog.dao.AdminDAO;
import com.whoai.blog.dto.AbstractDTO;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.exception.ResourcesNotFoundException;
import com.whoai.blog.service.AdminService;
import com.whoai.blog.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户操作服务实现
 *
 * @see com.whoai.blog.service.AdminService
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer checkLogin(AbstractDTO<AdminDTO, Admin> dto, HttpServletResponse response) {
        boolean info = log.isInfoEnabled();
        Admin admin = dto.convertToEntity();
        Admin checker = adminDAO.checkLogin(admin);
        if (checker != null) {
            if (checker.getStatus() == Status.VALID) {
                if (info) {
                    log.info("用户[{}]登录成功", checker.getAccount());
                }
                String jwt = JWTUtil.createJWT("1", checker.getAccount(), checker.getAccount(), 30 * 60 * 1000);
                Cookie cookie = new Cookie("access_token", jwt);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                cookie.setHttpOnly(false);
                response.addCookie(cookie);
                return 1;
            } else {
                if (info) {
                    log.info("用户[{}]已禁用", checker.getAccount());
                }
                return 0;
            }
        } else {
            if (info) {
                log.info("用户[{}]登录失败，无此账户，或账号密码错误！", admin.getAccount());
            }
            throw new ResourcesNotFoundException("");
        }
    }

    @Override
    public boolean checkLogout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("access_token", null);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    /**
     * 修改数据的过程中实现加密
     */
    @Override
    @Transactional
    public Integer updateAdmin(AbstractDTO<AdminInputDTO, Admin> dto, HttpServletRequest request) {
        if (log.isInfoEnabled()) {
            log.info("待修改的用户信息: {}", dto);
        }
        Admin admin = dto.convertToEntity();
        return adminDAO.updateAdmin(admin);
    }

    @Override
    @Transactional
    public Integer saveAdmin(AbstractDTO<AdminDTO, Admin> dto, HttpServletRequest request) {
        if (log.isInfoEnabled()) {
            log.info("待新增的用户信息: {}", dto);
        }
        Admin admin = dto.convertToEntity();
        admin.setRole(Role.GUEST);
        admin.setStatus(Status.VALID);
        return adminDAO.saveAdmin(admin);
    }
}
