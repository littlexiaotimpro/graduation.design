package com.whoai.blog.sso.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.sso.service.UserInfoService;
import com.whoai.blog.sso.web.param.UserInfoSaveParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基本信息控制器
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
@Api("用户基本信息相关接口")
@RestController
@RequestMapping(value = "/blog/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("保存用户基本信息")
    @PostMapping(value = "/save")
    public ResponseResult<Void> saveUserInfo(@RequestBody UserInfoSaveParam param) {
        userInfoService.saveUserInfo(param);
        return ResponseResult.success(null, "用户基本信息保存成功");
    }

}
