package com.whoai.blog.sso.service;

import com.whoai.blog.enums.ApplicationTypeEnum;
import com.whoai.blog.sso.UserLoginInfo;
import com.whoai.blog.sso.web.param.LoginParam;
import com.whoai.blog.sso.web.param.RegisterParam;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录注册相关服务
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public interface UserLoginService {

    /**
     * 用户登录，签发token
     *
     * @param param 登录参数
     * @return token
     */
    String login(LoginParam param, HttpServletResponse response);

    /**
     * 用户注册
     *
     * @param param 用户注册参数
     */
    void register(RegisterParam param);

    /**
     * 注销登录
     *
     * @param type     应用类型
     * @param response 响应
     */
    void logout(ApplicationTypeEnum type, HttpServletResponse response);

    UserLoginInfo findByUsername(String username);

}
