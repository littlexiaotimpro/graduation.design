package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户权限
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("u_user_permission")
public class UserPermission extends BaseModifyEntity {

    private static final long serialVersionUID = 5341349622607523228L;
    /**
     * 权限标识
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;
}
