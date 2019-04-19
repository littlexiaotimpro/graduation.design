package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.AdminDAO;
import cn.hc.xiaosi.dto.AdminDTO;
import cn.hc.xiaosi.entity.Admin;
import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.AdminService;
import cn.hc.xiaosi.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public Message checkLogin(AdminDTO adminDTO) {
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
                message.setCode(1).setMsg("登录成功");
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
