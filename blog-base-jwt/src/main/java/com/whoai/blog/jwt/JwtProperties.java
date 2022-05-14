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
    private String tokenHeader;
    private String tokenHead;
    /**
     * 加密密钥
     */
    private String secret;

    /**
     * token 过期时间，单位s
     */
    private Long expire;
}
