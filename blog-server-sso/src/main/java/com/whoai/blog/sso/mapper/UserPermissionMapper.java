package com.whoai.blog.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoai.blog.entity.UserPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户权限
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public interface UserPermissionMapper extends BaseMapper<UserPermission> {

    /**
     * 依据角色ID获取用户权限，需借助角色权限关联表
     *
     * @param roleId 角色ID
     * @return permissions
     */
    List<UserPermission> findByRoleId(@Param("roleId") Long roleId);

    /**
     * 依据角色ID获取用户权限，需借助角色权限关联表
     *
     * @param roleIds 角色ID
     * @return permissions
     */
    List<UserPermission> findByRoleIds(@Param("roleIds") List<Long> roleIds);

}
