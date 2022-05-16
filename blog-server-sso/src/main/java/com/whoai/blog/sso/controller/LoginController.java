package com.whoai.blog.sso.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.sso.service.UserLoginService;
import com.whoai.blog.sso.web.param.BaseApplicationTypeParam;
import com.whoai.blog.sso.web.param.LoginParam;
import com.whoai.blog.sso.web.param.RegisterParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author xiaosi
 * @since 2022/5/8
 */
@Api("用户登录/注册/注销")
@Slf4j
@RestController
@RequestMapping(value = "/blog")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody LoginParam param, HttpServletResponse response) {
        log.info("login param: {}", param);
        String token = userLoginService.login(param, response);
        return ResponseResult.success(token, "登录成功");
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody RegisterParam param) {
        log.info("register param: {}", param);
        userLoginService.register(param);
        return ResponseResult.success(null, "注册成功");
    }

    @ApiOperation("注销登录")
    @PostMapping("/logout")
    public ResponseResult<Void> logout(@RequestBody BaseApplicationTypeParam param, HttpServletResponse response) {
        log.info("user logout");
        userLoginService.logout(param.getType(), response);
        return ResponseResult.success(null, "注销登录成功");
    }

}
