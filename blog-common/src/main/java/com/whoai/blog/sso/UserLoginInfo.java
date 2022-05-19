package com.whoai.blog.sso;

import lombok.Builder;
import lombok.Data;

/**
 * 用户登录信息
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
@Data
@Builder
public class UserLoginInfo {

    private Long id;
    private String username;

}
