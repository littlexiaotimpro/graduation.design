package com.whoai.blog.sso;

import com.whoai.blog.jwt.EnableJwt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 登录模块启动类
 *
 * @since 2022/5/8
 */
@SpringBootApplication
@MapperScan("com.whoai.blog.sso.mapper")
@EnableDiscoveryClient
@EnableJwt
public class SsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }
}
