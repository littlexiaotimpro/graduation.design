package com.whoai.blog.sso.exception;

/**
 * 用户异常信息
 *
 * @author xiaosi
 * @date 2022/5/21
 * @since 1.0
 */
public final class UserExceptionMessage {

    private UserExceptionMessage() {
    }

    public final static String USER_REGISTER_FAIL = "用户注册失败！";

    public final static String USER_PASSWORD_UN_MATCH = "用户密码不正确";

    public final static String USER_DISABLE = "当前用户已禁用";

    public final static String USER_ACCOUNT_LOCKED = "用户账户已被锁定";

    public final static String USER_ACCOUNT_EXPIRED = "用户账户已过期";

    public final static String USER_CREDENTIALS_EXPIRED = "用户凭证已过期";

}
