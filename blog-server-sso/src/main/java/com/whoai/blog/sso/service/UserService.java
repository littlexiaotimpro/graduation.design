package com.whoai.blog.sso.service;

import com.whoai.blog.sso.UserLoginInfo;
import com.whoai.blog.sso.web.vo.UserDetailVO;

import java.util.List;

/**
 * 用户操作服务接口
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
public interface UserService {

    List<UserDetailVO> listUserDetails();

    UserLoginInfo findByUsername(String username);

}
