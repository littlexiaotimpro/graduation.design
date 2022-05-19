package com.whoai.blog.sso.config.filter;

import cn.hutool.core.util.ObjectUtil;
import com.whoai.blog.entity.User;
import com.whoai.blog.jwt.JwtProperties;
import com.whoai.blog.jwt.JwtTokenUtil;
import com.whoai.blog.sso.UserLoginInfo;
import com.whoai.blog.sso.UserLoginInfoHolder;
import com.whoai.blog.sso.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求拦截器（在进入 Controller 前），可以过滤请求或设置一些参数
 * <p>
 * 如下（可以由前端解决）：
 * <tr>- 用户未登录时，在一些需要登录的操作上进行过滤
 * <tr>- 用户登录后，缓存用户信息，在一些需要权限的操作上进行过滤
 */
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 设置响应参数
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");

        // 用户权限认证
        String authHeader = request.getHeader(jwtProperties.getTokenHeader());
        String username = null;
        if (authHeader != null && authHeader.startsWith(jwtProperties.getTokenHead())) {
            String authToken = authHeader.substring(jwtProperties.getTokenHead().length());
            username = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        boolean notNull = ObjectUtil.isNotNull(username);
        try {
            if (notNull) {
                User user = userMapper.findByUsername(username);
                // 设置用户信息
                UserLoginInfoHolder.setUser(UserLoginInfo.builder()
                        .id(user.getId())
                        .username(username)
                        .build());
            }
            //此行代码确保请求可以继续执行至Controller
            filterChain.doFilter(request, response);
        } finally {
            if (notNull) {
                UserLoginInfoHolder.removeUser();
            }
        }
    }
}
