package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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

    /**
     * 管理端获取用户数据
     *
     * @return
     */
    ArrayList<Admin> controlFindAll();

    /**
     * 管理端修改管理员信息
     *
     * @param adminInputDTO
     * @return
     */
    Message controlUpdateAdmin(AdminInputDTO adminInputDTO,HttpServletRequest request);
}
