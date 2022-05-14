package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("u_user")
public class User extends BaseModifyEntity {

    private static final long serialVersionUID = 7040140096185971571L;
    /**
     * 用户名称，用于登录系统
     */
    private String username;

    /**
     * 登录密码，通过加密的方式存储
     */
    private String password;

}
