package com.whoai.blog.sso.sercurity.service;

import com.whoai.blog.sso.mapper.LoginMapper;
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
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 依据用户名获取用户 TODO 若后续加入缓存功能，此处需要新增逻辑，是否从缓存中获取
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
