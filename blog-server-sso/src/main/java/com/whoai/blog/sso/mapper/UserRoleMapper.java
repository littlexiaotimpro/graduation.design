package com.whoai.blog.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoai.blog.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询用户角色
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 依据用户ID获取用户角色，需要用户角色关联表
     *
     * @param userId 用户ID
     * @return roles
     */
    List<UserRole> findByUserId(@Param("userId") Long userId);

}
