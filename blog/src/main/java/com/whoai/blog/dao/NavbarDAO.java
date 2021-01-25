package com.whoai.blog.dao;

import com.whoai.blog.entity.Navbar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * NavbarDAO继承基类
 */
@Repository
public interface NavbarDAO extends MyBatisBaseDao<Navbar, String> {

    /**
     * 查询所有导航栏
     *
     * @return
     */
    ArrayList<Navbar> findAll();

    /**
     * 查询所有的启用的导航栏
     *
     * @return
     */
    ArrayList<Navbar> findAllUsing();

    /**
     * 新增导航
     *
     * @param navbar
     * @return
     */
    Integer saveNavbar(@Param("navbar") Navbar navbar);

    /**
     * 启用,禁用导航
     *
     * @param navbar
     * @return
     */
    Integer deleteNavbar(@Param("navbar") Navbar navbar);

    /**
     * 编辑导航信息
     *
     * @param navbar
     * @return
     */
    Integer updateNavbar(@Param("navbar") Navbar navbar);

}