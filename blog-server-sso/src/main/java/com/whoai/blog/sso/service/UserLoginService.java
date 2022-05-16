package com.whoai.blog.sso.service;

import com.whoai.blog.entity.User;
import com.whoai.blog.enums.ApplicationTypeEnum;
import com.whoai.blog.jwt.JwtProperties;
import com.whoai.blog.jwt.JwtTokenUtil;
import com.whoai.blog.sso.exception.UserManagerException;
import com.whoai.blog.sso.mapper.UserInfoMapper;
import com.whoai.blog.sso.mapper.UserMapper;
import com.whoai.blog.sso.mapper.UserRoleConnectMapper;
import com.whoai.blog.sso.web.param.LoginParam;
import com.whoai.blog.sso.web.param.RegisterParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 用户登录注册相关服务
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
@Slf4j
@Service
public class UserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleConnectMapper userRoleConnectMapper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;

    private static final String ACCESS_TOKEN_KEY = "_TOKEN";

    /**
     * 用户登录，签发token
     *
     * @param param 登录参数
     * @return token
     */
    public String login(LoginParam param, HttpServletResponse response) {
        String token = null;
        String username = param.getUsername();
        String password = param.getPassword();
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generalToken(username);
            Cookie cookie = new Cookie(param.getType().name() + ACCESS_TOKEN_KEY, token);
            cookie.setMaxAge(jwtProperties.getExpire().intValue());
            cookie.setPath("/");
            cookie.setHttpOnly(false);
            response.addCookie(cookie);
            response.addHeader(jwtProperties.getTokenHeader(), jwtProperties.getTokenHead() + " " + token);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 用户注册
     *
     * @param param 用户注册参数
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterParam param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setPassword(passwordEncoder.encode(param.getPassword()));
        user.setActive(1);
        user.setCreateBy(1L);
        Date gmtCreated = new Date();
        user.setGmtCreated(gmtCreated);
        user.setModifyBy(1L);
        user.setGmtModify(gmtCreated);
        // TODO ID 自增问题
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            throw new UserManagerException("用户注册失败！");
        }
    }

    /**
     * 注销登录
     *
     * @param type     应用类型
     * @param response 响应
     */
    public void logout(ApplicationTypeEnum type, HttpServletResponse response) {
        Cookie cookie = new Cookie(type.name() + ACCESS_TOKEN_KEY, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
    }

}
