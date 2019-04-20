package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.AdminDAO;
import cn.hc.xiaosi.dto.AdminDTO;
import cn.hc.xiaosi.entity.Admin;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.AdminService;
import cn.hc.xiaosi.service.LogService;
import cn.hc.xiaosi.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


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
            if (checker.getStatus() == 1) {
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
                message.setCode(0).setMsg("此用户已禁用");
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
}
