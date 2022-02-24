package com.whoai.blog.constant;

import lombok.ToString;

@ToString
public enum Permission implements Dictionary {

    GUEST(1, "普通用户"),
    SYSTEM(0, "系统管理员");

    private final Integer code;
    private final String value;

    Permission(Integer code, String value) {
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

    public static Permission convert(Integer code) {
        Permission[] values = Permission.values();
        for (Permission value : values) {
            if (code != null && code.compareTo(value.getCode()) == 0) {
                return value;
            }
        }
        return null;
    }
}
