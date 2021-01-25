package com.whoai.blog.service.imp;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dao.AdminDAO;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.entity.LogBean;
import com.whoai.blog.service.AdminService;
import com.whoai.blog.service.LogService;
import com.whoai.blog.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


/**
 * @ClassName AdminServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2814:38
 */
@Service
@Slf4j
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private LogService logService;

    @Override
    public Message checkLogin(AdminDTO adminDTO, HttpServletResponse response) {
        boolean debug = log.isDebugEnabled();
        LogBean logBean = new LogBean();
        log.info("管理员[{}]尝试登录系统。", adminDTO.getAccount());
        logBean.setOperation("登录").setOperator(adminDTO.getAccount()).setContent("管理员" + adminDTO.getAccount() + "尝试登录系统。");
        logService.saveLog(logBean);
        Admin admin = adminDTO.convertToAdmin();
        Admin checker = adminDAO.checkLogin(admin);
        Message message = new Message();
        if (checker != null) {
            if (checker.getStatus() == 1 && checker.getPermission() == 0) {
                if (debug) {
                    log.debug("管理员[{}]登录成功", checker.getAccount());
                }
                log.info("将用户信息使用JWT保存。");
                String jwt = JWTUtil.createJWT("1", checker.getAccount(), checker.getAccount(), 30 * 60 * 1000);
                message.setCode(1).setMsg("登录成功");
                Cookie cookie = new Cookie("access_token", jwt);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                cookie.setHttpOnly(false);
                response.addCookie(cookie);
            } else {
                if (debug) {
                    log.debug("管理员[{}]已禁用", checker.getAccount());
                }
                message.setCode(0).setMsg("此用户已禁用，或权限不够");
            }
            logBean.setOperation("登录").setOperator(checker.getAccount()).setContent("管理员" + adminDTO.getAccount() + message.getMsg());
        } else {
            log.info("管理员[{}]登录失败，无此账户，或账号密码错误！", adminDTO.getAccount());
            message.setCode(-1).setMsg("登录失败，无此用户，或账号密码错误！");
            logBean.setOperation("登录").setOperator(adminDTO.getAccount()).setContent("管理员" + adminDTO.getAccount() + message.getMsg());
        }
        logService.saveLog(logBean);
        return message;
    }

    @Override
    public Message checkLogout(HttpServletRequest request, HttpServletResponse response) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            log.info("注销登录");
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            log.info("管理员[{}]注销登录。", operator);
            Cookie cookie = new Cookie("access_token", null);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            cookie.setHttpOnly(false);
            response.addCookie(cookie);
            message.setCode(1).setMsg("注销登录");
        }
        return message;
    }

    @Override
    public ArrayList<Admin> controlFindAll() {
        log.info("管理端获取所有用户信息");
        return adminDAO.findAll();
    }

    /**
     * 修改数据的过程中实现加密
     *
     * @param adminInputDTO
     * @param request
     * @return
     */
    @Override
    public Message controlUpdateAdmin(AdminInputDTO adminInputDTO, HttpServletRequest request) {
        Message message = new Message();
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            message.setCode(0).setMsg("管理员未登录或登录过期");
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("管理员[{}]尝试修改数据。", operator);
            Admin admin = adminInputDTO.convertToAdmin();
            Integer result = adminDAO.updateAdmin(admin);
            if (result == null || result == 0) {
                log.info("管理员[{}]信息编辑失败", operator);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "编辑数据失败");
                message.setCode(-1).setMsg("操作失败!");
            } else {
                if (debug) {
                    log.debug("管理员[{}]编辑数据成功，数据为：[{}]", operator, adminInputDTO);
                }
                log.info("管理员[{}]编辑数据成功，数据为：[{}]，影响结果数：[{}]", operator, adminInputDTO, result);
                logBean.setOperation("编辑").setOperator(operator).setContent("管理员" + operator + "编辑数据成功，数据为：" + adminInputDTO);
                message.setCode(1).setMsg("操作成功!");
            }
            logService.saveLog(logBean);
        }
        return message;
    }
}
