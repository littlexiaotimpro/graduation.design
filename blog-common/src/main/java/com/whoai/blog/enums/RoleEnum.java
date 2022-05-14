package com.whoai.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 角色
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
public enum RoleEnum {

    /**
     * 超级管理员
     */
    SUPER_ADMIN(1, "SUPER_ADMIN"),

    /**
     * 普通管理员
     */
    ADMIN(2, "ADMIN"),

    /**
     * 普通用户
     */
    NORMAL(3, "NORMAL"),

    /**
     * 游客
     */
    GUEST(4, "GUEST");

    @EnumValue
    private final Integer id;

    @JsonValue
    private final String code;

    RoleEnum(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
