package com.whoai.blog.constant;

/**
 * 用户登录响应状态
 */
public enum LoginStatus {
    LOGIN_SUCCESS(1, "登录成功，可以开始操作了"),
    LOGIN_FAIL(-1, "登录失败，无此用户，或账号密码错误！"),
    LOGIN_DISABLE(0, "此用户已禁用，或权限不够");

    private final Integer code;
    private final String value;

    LoginStatus(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
