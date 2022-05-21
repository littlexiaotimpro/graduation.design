package com.whoai.blog.sso.exception;

/**
 * 用户账户过期异常
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public class UserAccountExpiredException extends RuntimeException {
    private static final long serialVersionUID = 6006873820447731372L;

    public UserAccountExpiredException() {
        super();
    }

    public UserAccountExpiredException(String message) {
        super(message);
    }
}
