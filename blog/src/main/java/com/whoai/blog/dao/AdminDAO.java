package com.whoai.blog.dao;

import com.whoai.blog.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AdminDAO继承基类
 */
@Repository
public interface AdminDAO extends MyBatisBaseDao<Admin, String> {

    /**
     * 管理员登录验证
     */
    Admin checkLogin(@Param("admin") Admin admin);

    /**
     * 查询所有用户
     */
    List<Admin> findAll();

    /**
     * 更新管理员信息
     */
    Integer updateAdmin(@Param("admin") Admin admin);

}