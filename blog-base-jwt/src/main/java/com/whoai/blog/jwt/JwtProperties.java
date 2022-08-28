package com.whoai.blog.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JWT 配置相关属性
 *
 * @since 2022/4/12
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String tokenHeader = "Authorization";
    private String tokenHead = "Head";
    /**
     * 加密密钥
     */
    private String secret = "test_secret";

    /**
     * token 过期时间，单位s
     */
    private Long expire = 3600L;
}
