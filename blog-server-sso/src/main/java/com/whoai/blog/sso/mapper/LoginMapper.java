package com.whoai.blog.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoai.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 登录操作的 Mapper
 *
 * @since 2022/5/8
 */
@Mapper
public interface LoginMapper extends BaseMapper<User> {

    /**
     * 依据用户名获取用户信息
     *
     * @param username 账号
     * @return 用户信息
     */
    User findByUsername(@Param("username") String username);
}
