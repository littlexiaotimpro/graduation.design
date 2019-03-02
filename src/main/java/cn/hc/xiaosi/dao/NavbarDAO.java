package cn.hc.xiaosi.dao;

import cn.hc.xiaosi.entity.Navbar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * NavbarDAO继承基类
 */
@Repository
public interface NavbarDAO extends MyBatisBaseDao<Navbar, String> {

    /**
     * 查询所有的启用的导航栏
     *
     * @return
     */
    ArrayList<Navbar> findAll();

    /**
     * 新增导航
     *
     * @param navbar
     * @return
     */
    int saveNavbar(@Param("navbar") Navbar navbar);

    /**
     * 启用,禁用导航
     *
     * @param navbar
     * @return
     */
    int deleteNavbar(@Param("navbar") Navbar navbar);

}