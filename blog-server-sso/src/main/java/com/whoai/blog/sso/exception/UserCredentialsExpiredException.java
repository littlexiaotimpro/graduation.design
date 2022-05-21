package com.whoai.blog.sso.exception;

/**
 * 用户凭证过期异常
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public class UserCredentialsExpiredException extends RuntimeException {
    private static final long serialVersionUID = 6006873820447731372L;

    public UserCredentialsExpiredException() {
        super();
    }

    public UserCredentialsExpiredException(String message) {
        super(message);
    }
}
