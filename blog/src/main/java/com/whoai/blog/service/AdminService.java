package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.AbstractDTO;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员服务接口（系统未提供用户注册等功能）
 */
public interface AdminService {
    /**
     * 管理员登录验证
     */
    Message checkLogin(AbstractDTO<AdminDTO, Admin> dto, HttpServletResponse response);

    /**
     * 管理员登出验证
     */
    Message checkLogout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 管理端获取用户数据
     */
    List<Admin> controlFindAll();

    /**
     * 管理端修改管理员信息
     */
    Message controlUpdateAdmin(AbstractDTO<AdminInputDTO, Admin> dto,HttpServletRequest request);
}
