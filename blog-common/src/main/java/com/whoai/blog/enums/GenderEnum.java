package com.whoai.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 性别
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
public enum GenderEnum {

    /**
     * 无
     */
    NONE(0,"无"),

    /**
     * 男
     */
    MAN(1,"男"),

    /**
     * 女
     */
    WOMEN(2,"女");

    @EnumValue
    private final int code;

    @JsonValue
    private final String name;

    GenderEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
