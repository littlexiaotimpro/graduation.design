package com.whoai.blog.sso.po;

import com.whoai.blog.enums.PermissionEnum;
import com.whoai.blog.enums.RoleEnum;
import lombok.Data;

/**
 * 构建用户登录授权用对象
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
public class UserAuthenticationPO {

    private String username;

    private String password;

    private RoleEnum role;

    private PermissionEnum permissionEnum;

}
