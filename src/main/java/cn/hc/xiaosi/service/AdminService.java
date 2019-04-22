package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.AdminDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2814:38
 */
public interface AdminService {
    /**
     * 管理员登录验证
     *
     * @param adminDTO
     * @param response
     * @return
     */
    Message checkLogin(AdminDTO adminDTO, HttpServletResponse response);

    /**
     * 管理员登出验证
     *
     * @param request
     * @param response
     * @return
     */
    Message checkLogout(HttpServletRequest request, HttpServletResponse response);
}
