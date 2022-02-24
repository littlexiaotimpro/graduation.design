package com.whoai.blog.constant;

import lombok.ToString;

@ToString
public enum Status implements Dictionary {

    VALID(1, "启用"),
    INVALID(0, "禁用");

    private final Integer code;
    private final String value;

    Status(Integer code, String value) {
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

    public static Status convert(Integer code) {
        Status[] values = Status.values();
        for (Status value : values) {
            if (code != null && code.compareTo(value.getCode()) == 0) {
                return value;
            }
        }
        return null;
    }
}
