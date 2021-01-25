package com.whoai.blog.controller;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.NavbarInputDTO;
import com.whoai.blog.dto.NavbarOutputDTO;
import com.whoai.blog.dto.NavbarStatusInputDTO;
import com.whoai.blog.entity.Navbar;
import com.whoai.blog.service.NavbarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName NavbarController
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/114:37
 */
@RestController
@RequestMapping(value = "navbar")
public class NavbarController {

    @Autowired
    private NavbarService navbarService;

    /**
     * 管理端
     *
     * @return
     */
    @RequestMapping(value = "control")
    @ApiOperation(value = "控制台输出")
    public ArrayList<Navbar> navbars() {
        return navbarService.controlFindAll();
    }

    @RequestMapping(value = "caption")
    @ApiOperation(value = "名称输出")
    public ArrayList<NavbarOutputDTO> getCaption() {
        return navbarService.controlFindAllCaption();
    }

    @RequestMapping(value = "save")
    @ApiOperation(value = "新增导航栏")
    public Message saveNavbar(@RequestBody NavbarInputDTO navbarInputDTO, HttpServletRequest request) {
        return navbarService.controlSaveNavbar(navbarInputDTO, request);
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value = "启用，禁用导航")
    public Message deleteNavbar(@RequestBody NavbarStatusInputDTO navbarStatusInputDTO, HttpServletRequest request) {
        return navbarService.controlDeleteNavbar(navbarStatusInputDTO, request);
    }

    @RequestMapping(value = "update")
    @ApiOperation(value = "编辑导航信息")
    public Message updateNavbar(@RequestBody NavbarInputDTO navbarInputDTO, HttpServletRequest request) {
        return navbarService.controlUpdateNavbar(navbarInputDTO, request);
    }

    /**
     * 客户端
     *
     * @return
     */
    @RequestMapping(value = "client")
    @ApiOperation(value = "客户端输出")
    public ArrayList<NavbarOutputDTO> navbarOutputDTOS() {
        return navbarService.clientFindAllUsing();
    }


}
