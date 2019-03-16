package cn.hc.xiaosi.service;

import cn.hc.xiaosi.bean.Message;
import cn.hc.xiaosi.dto.NavbarInputDTO;
import cn.hc.xiaosi.dto.NavbarOutputDTO;
import cn.hc.xiaosi.dto.NavbarStatusInputDTO;
import cn.hc.xiaosi.entity.Navbar;

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
     * @return
     */
    Message controlSaveNavbar(NavbarInputDTO navbarInputDTO);

    /**
     * 前台管理端启用，禁用导航
     *
     * @param navbarStatusInputDTO
     * @return
     */
    Message controlDeleteNavbar(NavbarStatusInputDTO navbarStatusInputDTO);

    /**
     * 前台管理端编辑导航信息
     *
     * @param navbarInputDTO
     * @return
     */
    Message controlUpdateNavbar(NavbarInputDTO navbarInputDTO);

}
