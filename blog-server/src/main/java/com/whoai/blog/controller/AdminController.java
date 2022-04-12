package com.whoai.blog.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.constant.LoginStatus;
import com.whoai.blog.constant.Permission;
import com.whoai.blog.dto.AdminLoginDTO;
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

    @PostMapping(value = "/register")
    @ApiOperation(value = "用户注册")
    public ResponseResult<Void> register(@RequestBody AdminInputDTO dto) {
        Integer register = adminService.register(dto);
        if (register <= 0) {
            return ResponseResult.fail("注册失败");
        }
        return ResponseResult.success(null, "注册成功");
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录验证")
    public ResponseResult<String> login(@RequestBody AdminLoginDTO adminLoginDTO, HttpServletResponse response) {
        String token = adminService.login(adminLoginDTO, response);
        if (token != null) {
            return ResponseResult.success(token, LoginStatus.LOGIN_SUCCESS.getValue());
        } else {
            return ResponseResult.fail(LoginStatus.LOGIN_FAIL.getValue());
        }
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "注销登录")
    public ResponseResult<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        boolean logout = adminService.logout(request, response);
        if (logout) {
            return ResponseResult.success(null, "注销成功");
        }
        return ResponseResult.fail("注销登录失败");
    }

    @GetMapping(value = "/permission/{account}")
    @ApiOperation(value = "查询用户权限")
    public ResponseResult<List<Permission>> getAdminPermissions(@PathVariable("account") String account) {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
        adminLoginDTO.setAccount(account);
        Admin admin = adminService.findUserByAccount(adminLoginDTO);
        return ResponseResult.success(admin.getPermission(), "查询用户权限列表");
    }

    @GetMapping(value = "/find/all")
    @ApiOperation(value = "获取用户")
    public ResponseResult<List<Admin>> getUsers() {
        List<Admin> admins = adminService.findAll();
        return ResponseResult.success(admins, "查询成功");
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "编辑用户信息")
    public ResponseResult<Void> updateAdmin(@RequestBody AdminInputDTO adminInputDTO, HttpServletRequest request) {
        Integer integer = adminService.updateAdmin(adminInputDTO, request);
        if (integer <= 0) {
            return ResponseResult.fail("数据更新失败");
        }
        return ResponseResult.success(null, "数据更新成功");
    }

    @PostMapping(value = "save")
    @ApiOperation(value = "新增用户信息")
    public ResponseResult<Void> saveAdmin(@RequestBody AdminLoginDTO adminLoginDTO, HttpServletRequest request) {
        Integer integer = adminService.saveAdmin(adminLoginDTO, request);
        if (integer <= 0) {
            return ResponseResult.fail("用户新增失败！");
        }
        return ResponseResult.success(null, "用户新增成功！");
    }

}
