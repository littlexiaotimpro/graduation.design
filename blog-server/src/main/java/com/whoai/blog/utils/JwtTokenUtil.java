package com.whoai.blog.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * JWT 生成 Token 的工具类
 *
 * @since 2022/4/11
 */
@Component
@Slf4j
public class JwtTokenUtil {
    // 加密密钥
    @Value("${jwt.secret}")
    private String secret;
    // token 过期时间
    @Value("${jwt.expire}")
    private Long expire;
    // 加密算法
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    /**
     * 创建 token 加密密钥
     *
     * @return 密钥
     */
    private SecretKey generalSecretKey() {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretBytes, 0, secretBytes.length, "AES");
    }

    /**
     * 签发JWT，创建 token
     *
     * @return token，token是一次性的。是为一个用户的有效登录周期准备的一个token。用户退出或超时，token失效。
     */
    public String generalToken() {
        return null;
    }

    /**
     * 解析 token，返回用户信息
     *
     * @return 用户名
     */
    public String parseToken() {
        return null;
    }

    /**
     * 验证 token 是否合理
     *
     * @return boolean
     */
    public boolean validateToken() {
        return false;
    }
}
