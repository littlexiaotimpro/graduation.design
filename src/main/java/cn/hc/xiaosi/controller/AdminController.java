package cn.hc.xiaosi.controller;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.AdminDTO;
import cn.hc.xiaosi.dto.AdminInputDTO;
import cn.hc.xiaosi.entity.Admin;
import cn.hc.xiaosi.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2814:40
 */
@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "login")
    @ApiOperation(value = "登录验证")
    public Message checkLogin(@RequestBody AdminDTO adminDTO, HttpServletResponse response) {
        return adminService.checkLogin(adminDTO, response);
    }

    @RequestMapping(value = "logout")
    @ApiOperation(value = "注销登录")
    public Message checkLogout(HttpServletRequest request, HttpServletResponse response) {
        return adminService.checkLogout(request, response);
    }

    @RequestMapping(value = "control")
    @ApiOperation(value = "获取用户")
    public ArrayList<Admin> getUsers() {
        return adminService.controlFindAll();
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑用户信息")
    public Message updateAdmin(@RequestBody AdminInputDTO adminInputDTO, HttpServletRequest request) {
        return adminService.controlUpdateAdmin(adminInputDTO, request);
    }

}
