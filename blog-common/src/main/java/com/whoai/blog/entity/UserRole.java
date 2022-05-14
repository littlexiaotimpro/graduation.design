package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("u_user_role")
public class UserRole extends BaseModifyEntity {

    private static final long serialVersionUID = 8837172021250540772L;
    /**
     * 角色标识
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;
}
