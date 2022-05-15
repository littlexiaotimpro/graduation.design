package com.whoai.blog.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt配置类
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties({JwtProperties.class})
public class JwtConfiguration {

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

}
