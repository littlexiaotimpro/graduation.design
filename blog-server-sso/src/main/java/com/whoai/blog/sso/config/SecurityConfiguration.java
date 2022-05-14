package com.whoai.blog.sso.config;

import com.whoai.blog.jwt.JwtProperties;
import com.whoai.blog.sso.config.filter.LoginFilter;
import com.whoai.blog.sso.sercurity.*;
import com.whoai.blog.constant.Role;
import com.whoai.blog.sso.sercurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * spring boot security 安全认证
 * <tr>给所有的post请求增加认证
 *
 * @since 2022/4/12
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties({JwtProperties.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                // 由于使用的是JWT，我们这里不需要csrf
                .disable()
                // 基于token，所以不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 设置请求的角色权限
                .antMatchers("/admin/**")
                .hasRole(Role.SYSTEM.name())
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET)
                .permitAll()
                // 对登录注册要允许匿名访问
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest()
                .authenticated()
                // 添加JWT filter，在UsernamePasswordAuthenticationFilter之前
                .and().addFilterBefore(traceFilter(), UsernamePasswordAuthenticationFilter.class)
                //添加自定义未授权和未登录结果返回
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                // 禁用缓存
                .and().headers().cacheControl();
    }

    @Bean
    public LoginFilter traceFilter() {
        return new LoginFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
