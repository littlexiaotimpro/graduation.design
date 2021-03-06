package com.whoai.blog.service;

import com.whoai.blog.bean.Message;
import com.whoai.blog.dto.NavbarInputDTO;
import com.whoai.blog.dto.NavbarOutputDTO;
import com.whoai.blog.dto.NavbarStatusInputDTO;
import com.whoai.blog.entity.Navbar;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName NavbarService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/114:07
 */
public interface NavbarService {

    /**
     * 前台客户端获取的部分信息
     *
     * @return
     */
    ArrayList<NavbarOutputDTO> clientFindAllUsing();

    /**
     * 前台管理端获取的全部信息
     *
     * @return
     */
    ArrayList<Navbar> controlFindAll();

    /**
     * 管理端获取所有的 标识--名称 的集合
     *
     * @return
     */
    ArrayList<NavbarOutputDTO> controlFindAllCaption();

    /**
     * 前台管理端新增导航
     *
     * @param navbarInputDTO
     * @param request
     * @return
     */
    Message controlSaveNavbar(NavbarInputDTO navbarInputDTO, HttpServletRequest request);

    /**
     * 前台管理端启用，禁用导航
     *
     * @param navbarStatusInputDTO
     * @param request
     * @return
     */
    Message controlDeleteNavbar(NavbarStatusInputDTO navbarStatusInputDTO, HttpServletRequest request);

    /**
     * 前台管理端编辑导航信息
     *
     * @param navbarInputDTO
     * @param request
     * @return
     */
    Message controlUpdateNavbar(NavbarInputDTO navbarInputDTO, HttpServletRequest request);

}
