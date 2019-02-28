package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.AdminDTO;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2814:38
 */
public interface AdminService {
    /**
     * 管理员登录验证
     */
    Message checkLogin(AdminDTO adminDTO);
}
