package com.whoai.blog.dao;

import com.whoai.blog.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminDAO {

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