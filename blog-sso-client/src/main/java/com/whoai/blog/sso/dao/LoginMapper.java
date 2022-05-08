package com.whoai.blog.sso.dao;

import com.whoai.blog.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 登录操作的 Mapper
 *
 * @since 2022/5/8
 */
@Mapper
public interface LoginMapper {

    /**
     * 依据账号获取用户信息
     *
     * @param account 账号
     * @return 用户信息
     */
    Admin findAdminByAccount(@Param("account") String account);
}
