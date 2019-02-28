package cn.hc.xiaosi.service.imp;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dao.AdminDAO;
import cn.hc.xiaosi.dto.AdminDTO;
import cn.hc.xiaosi.entity.Admin;
import cn.hc.xiaosi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminServiceImp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2814:38
 */
@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Message checkLogin(AdminDTO adminDTO) {
        Admin admin = adminDTO.convertToAdmin();
        Admin checker = adminDAO.checkLogin(admin);
        Message message = new Message();
        if (checker != null) {
            return checker.getStatus() == 1 ? message.setCode(1).setMsg("登录成功") : message.setCode(0).setMsg("此用户已禁用");
        } else {
            return message.setCode(-1).setMsg("登录失败，无此用户，或账号密码错误！");
        }
    }
}
