package com.whoai.blog.sso;

/**
 * 记录当前登录用户信息
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
public class UserLoginInfoHolder {

    private static final InheritableThreadLocal<UserLoginInfo> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void setUser(UserLoginInfo userLoginInfo) {
        THREAD_LOCAL.set(userLoginInfo);
    }

    public static UserLoginInfo getUser() {
        return THREAD_LOCAL.get();
    }

    public static void removeUser() {
        THREAD_LOCAL.remove();
    }

}
