package com.whoai.blog.sso.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.sso.service.UserService;
import com.whoai.blog.sso.web.vo.UserDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
@Api("用户信息相关接口")
@RestController
@RequestMapping(value = "/blog/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    @ApiOperation("获取用户列表")
    @PostMapping(value = "/list")
    public ResponseResult<List<UserDetailVO>> listUserDetails() {
        List<UserDetailVO> userDetails = userService.listUserDetails();
        return ResponseResult.success(userDetails, "获取用户列表成功");
    }

}
