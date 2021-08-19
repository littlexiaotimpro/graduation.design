package com.whoai.blog.constant;

/**
 * 用户登录响应状态
 */
public enum LoginStatus {
    LOGIN_SUCCESS(1, "登录成功，可以开始操作了"),
    LOGIN_FAIL(-1, "登录失败，无此用户，或账号密码错误！"),
    LOGIN_DISABLE(0, "此用户已禁用，或权限不够");

    private final Integer code;
    private final String description;

    LoginStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
