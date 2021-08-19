package com.whoai.blog.service.impl;

import com.whoai.blog.dao.AdminDAO;
import com.whoai.blog.dto.AbstractDTO;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.exception.ResourcesNotFoundException;
import com.whoai.blog.service.AdminService;
import com.whoai.blog.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        boolean debug = log.isDebugEnabled();
        Admin admin = dto.convertToEntity();
        Admin checker = adminDAO.checkLogin(admin);
        if (checker != null) {
            if (checker.getStatus() == 1 && checker.getPermission() == 0) {
                if (debug) {
                    log.debug("管理员[{}]登录成功", checker.getAccount());
                }
                String jwt = JWTUtil.createJWT("1", checker.getAccount(), checker.getAccount(), 30 * 60 * 1000);
                Cookie cookie = new Cookie("access_token", jwt);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                cookie.setHttpOnly(false);
                response.addCookie(cookie);
                return 1;
            } else {
                if (debug) {
                    log.debug("管理员[{}]已禁用", checker.getAccount());
                }
                return 0;
            }
        } else {
            log.info("管理员[{}]登录失败，无此账户，或账号密码错误！", admin.getAccount());
            return -1;
        }
    }

    @Override
    public boolean checkLogout(HttpServletRequest request, HttpServletResponse response) {
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        } else {
            log.info("管理员[{}]注销登录。", operator);
            Cookie cookie = new Cookie("access_token", null);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            cookie.setHttpOnly(false);
            response.addCookie(cookie);
            return Boolean.TRUE;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> controlFindAll() {
        log.info("管理端获取所有用户信息");
        return adminDAO.findAll();
    }

    /**
     * 修改数据的过程中实现加密
     */
    @Override
    @Transactional
    public Integer controlUpdateAdmin(AbstractDTO<AdminInputDTO, Admin> dto, HttpServletRequest request) {
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            throw new ResourcesNotFoundException("用户未登录，无法操作");
        }
        log.info("管理员[{}]尝试修改数据。", operator);
        Admin admin = dto.convertToEntity();
        return adminDAO.updateAdmin(admin);
    }
}
