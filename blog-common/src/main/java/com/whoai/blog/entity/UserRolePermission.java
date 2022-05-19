package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whoai.blog.enums.PermissionEnum;
import com.whoai.blog.enums.RoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("u_user_role_permission")
public class UserRolePermission extends BaseModifyEntity {

    private static final long serialVersionUID = 5715250997429924654L;
    /**
     * 角色
     */
    private RoleEnum role;

    /**
     * 权限
     */
    @TableField(value = "permission_id")
    private PermissionEnum permission;


}
