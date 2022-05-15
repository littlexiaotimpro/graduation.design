package com.whoai.blog.sso.exception;

/**
 * 用户管理异常，包括用户注册、登录、注销等
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public class UserManagerException extends RuntimeException {
    private static final long serialVersionUID = 6006873820447731372L;

    public UserManagerException() {
        super();
    }

    public UserManagerException(String message) {
        super(message);
    }
}
