package com.whoai.blog.sso.web.po;

import com.whoai.blog.entity.User;
import com.whoai.blog.entity.UserInfo;
import com.whoai.blog.entity.UserPermission;
import com.whoai.blog.entity.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 构建用户登录授权用对象
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@Builder
public class UserAuthenticationPO {

    /**
     * 用户登录信息
     */
    private User user;

    /**
     * 用户基本信息
     */
    private UserInfo userInfo;

    /**
     * 用户角色列表
     */
    private List<UserRole> userRoles;

    /**
     * 用户权限列表
     */
    private List<UserPermission> userPermissions;

}
