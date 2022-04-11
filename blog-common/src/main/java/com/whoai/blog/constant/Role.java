package com.whoai.blog.constant;

import lombok.ToString;

/**
 * 定义用户角色
 */
@ToString
public enum Role implements Dictionary {
    SYSTEM(0, "系统管理员"),
    NORMAL(1, "普通用户"),
    GUEST(2, "游客");

    private final Integer code;
    private final String value;

    Role(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static Role convert(Integer code) {
        Role[] values = Role.values();
        for (Role value : values) {
            if (code != null && code.compareTo(value.getCode()) == 0) {
                return value;
            }
        }
        return null;
    }
}
