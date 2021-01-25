package com.whoai.blog.dao;

import com.whoai.blog.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * AdminDAO继承基类
 */
@Repository
public interface AdminDAO extends MyBatisBaseDao<Admin, String> {

    /**
     * 管理员登录验证
     *
     * @param admin
     * @return
     */
    Admin checkLogin(@Param("admin") Admin admin);

    /**
     * 查询所有用户
     *
     * @return
     */
    ArrayList<Admin> findAll();

    /**
     * 更新管理员信息
     *
     * @param admin
     * @return
     */
    Integer updateAdmin(@Param("admin") Admin admin);

}