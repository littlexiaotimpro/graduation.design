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
    Admin findAdminByAccount(@Param("account") String account);

    /**
     * 查询所有用户
     */
    List<Admin> findAll();

    /**
     * 更新管理员信息
     */
    Integer updateAdmin(@Param("admin") Admin admin);

    /**
     * 新增用户
     *
     * @param admin 用户信息
     * @return 是否成功标识
     */
    Integer saveAdmin(@Param("admin") Admin admin);

}