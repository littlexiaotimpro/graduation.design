package com.whoai.blog.sso.sercurity.service;

import cn.hutool.core.util.ObjectUtil;
import com.whoai.blog.sso.exception.*;
import com.whoai.blog.sso.web.po.UserAuthenticationPO;
import com.whoai.blog.sso.sercurity.bean.UserDetailsImpl;
import com.whoai.blog.sso.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户详情服务实现
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 若后续加入缓存功能，此处需要新增逻辑，是否从缓存中获取
        UserAuthenticationPO userAuthenticationPO = userAuthenticationService.createUserAuthenticationPO(username);
        if (ObjectUtil.isNull(userAuthenticationPO)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 依据用户名获取用户
        UserDetailsImpl userDetails = new UserDetailsImpl(userAuthenticationPO);
        if (!userDetails.isEnabled()) {
            throw new UserDisableException(UserExceptionMessage.USER_DISABLE);
        } else if (!userDetails.isAccountNonLocked()) {
            throw new UserAccountLockedException(UserExceptionMessage.USER_ACCOUNT_LOCKED);
        } else if (!userDetails.isAccountNonExpired()) {
            throw new UserAccountExpiredException(UserExceptionMessage.USER_ACCOUNT_EXPIRED);
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new UserCredentialsExpiredException(UserExceptionMessage.USER_CREDENTIALS_EXPIRED);
        }
        return userDetails;
    }
}
