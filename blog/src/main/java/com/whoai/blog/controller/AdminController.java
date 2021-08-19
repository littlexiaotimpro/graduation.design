package com.whoai.blog.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.constant.LoginStatus;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * AdminController
 * 用户及管理员访问控制器
 *
 * @author XiaoSi
 * @date 2019/2/2814:40
 */
@Api("用户及管理员访问控制器")
@RestController
@RequestMapping(value = "admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "login")
    @ApiOperation(value = "登录验证")
    public ResponseResult<Void> checkLogin(@RequestBody AdminDTO adminDTO, HttpServletResponse response) {
        final Integer integer = adminService.checkLogin(adminDTO, response);
        if (integer == 1) {
            return ResponseResult.success(integer, null, LoginStatus.LOGIN_SUCCESS.getDescription());
        } else if (integer == 0) {
            return ResponseResult.fail(integer, LoginStatus.LOGIN_DISABLE.getDescription());
        } else {
            return ResponseResult.fail(integer, LoginStatus.LOGIN_FAIL.getDescription());
        }
    }

    @PostMapping(value = "logout")
    @ApiOperation(value = "注销登录")
    public ResponseResult<Void> checkLogout(HttpServletRequest request, HttpServletResponse response) {
        final boolean logout = adminService.checkLogout(request, response);
        if (logout) {
            return ResponseResult.success(null, "注销成功");
        }
        return ResponseResult.fail("注销登录失败");
    }

    @GetMapping(value = "control")
    @ApiOperation(value = "获取用户")
    public List<Admin> getUsers() {
        return adminService.controlFindAll();
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "编辑用户信息")
    public ResponseResult<Void> updateAdmin(@RequestBody AdminInputDTO adminInputDTO, HttpServletRequest request) {
        final Integer integer = adminService.controlUpdateAdmin(adminInputDTO, request);
        if (integer <= 0) {
            return ResponseResult.fail("数据更新失败");
        }
        return ResponseResult.success(null, "数据更新成功");
    }

}
