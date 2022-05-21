package com.whoai.blog.sso.exception;

/**
 * 用户启用异常
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public class UserDisableException extends RuntimeException {
    private static final long serialVersionUID = 6006873820447731372L;

    public UserDisableException() {
        super();
    }

    public UserDisableException(String message) {
        super(message);
    }
}
