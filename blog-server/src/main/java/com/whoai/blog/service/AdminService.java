package com.whoai.blog.service;

import com.whoai.blog.dto.AbstractDTO;
import com.whoai.blog.dto.AdminLoginDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理员服务接口（系统未提供用户注册等功能）
 */
public interface AdminService {

    Admin findUserByAccount(AbstractDTO<AdminLoginDTO, Admin> dto);

    /**
     * 用户注册
     *
     * @param dto 用户信息
     * @return 是否注册成功
     */
    Integer register(AbstractDTO<AdminInputDTO, Admin> dto);

    /**
     * 管理员登录验证
     */
    String login(AbstractDTO<AdminLoginDTO, Admin> dto, HttpServletResponse response);

    /**
     * 管理员登出验证
     */
    boolean logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 管理端获取用户数据
     */
    List<Admin> findAll();

    /**
     * 管理端修改管理员信息
     */
    Integer updateAdmin(AbstractDTO<AdminInputDTO, Admin> dto, HttpServletRequest request);

    /**
     * 新增用户
     *
     * @param dto     用户信息
     * @param request 请求
     * @return 保存是否成功
     */
    Integer saveAdmin(AbstractDTO<AdminLoginDTO, Admin> dto, HttpServletRequest request);
}
