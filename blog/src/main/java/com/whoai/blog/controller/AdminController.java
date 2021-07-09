package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.AdminDTO;
import com.whoai.blog.dto.AdminInputDTO;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @className AdminController
 * @description 用户及管理员访问控制器
 * @author XiaoSi
 * @date 2019/2/2814:40
 */
@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "login")
    @ApiOperation(value = "登录验证")
    public Message checkLogin(@RequestBody AdminDTO adminDTO, HttpServletResponse response) {
        return adminService.checkLogin(adminDTO, response);
    }

    @PostMapping(value = "logout")
    @ApiOperation(value = "注销登录")
    public Message checkLogout(HttpServletRequest request, HttpServletResponse response) {
        return adminService.checkLogout(request, response);
    }

    @GetMapping(value = "control")
    @ApiOperation(value = "获取用户")
    public ArrayList<Admin> getUsers() {
        return adminService.controlFindAll();
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "编辑用户信息")
    public Message updateAdmin(@RequestBody AdminInputDTO adminInputDTO, HttpServletRequest request) {
        return adminService.controlUpdateAdmin(adminInputDTO, request);
    }

}
