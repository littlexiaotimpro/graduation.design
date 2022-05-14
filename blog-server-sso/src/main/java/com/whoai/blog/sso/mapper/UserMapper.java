package com.whoai.blog.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoai.blog.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户相关操作 Mapper
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查询指定用户
     *
     * @param username 用户名
     * @return user
     */
    User findByUsername(@Param("username") String username);

}
