package com.whoai.blog.sso.exception;

/**
 * 用户账户锁定异常
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public class UserAccountLockedException extends RuntimeException {
    private static final long serialVersionUID = 6006873820447731372L;

    public UserAccountLockedException() {
        super();
    }

    public UserAccountLockedException(String message) {
        super(message);
    }
}
