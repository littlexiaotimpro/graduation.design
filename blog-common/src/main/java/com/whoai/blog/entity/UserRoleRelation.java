package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whoai.blog.enums.RoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限关联
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("u_user_role_relation")
public class UserRoleRelation extends BaseModifyEntity {


    private static final long serialVersionUID = -9192566348351584700L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色
     */
    @TableField(value = "role_id")
    private RoleEnum role;
}
