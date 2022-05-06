package com.whoai.blog.sso.util;

import com.whoai.blog.sso.config.sercurity.JwtProperties;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT 生成 Token 的工具类
 *
 * @since 2022/4/11
 */
@Component
@Slf4j
public class JwtTokenUtil {
    /**
     * token 已过期
     */
    public static final int JWT_ERR_CODE_EXPIRE = 1005;

    /**
     * token 验证不通过
     */
    public static final int JWT_ERR_CODE_FAIL = 1006;

    /**
     * 加密算法
     */
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 创建 token 加密密钥
     *
     * @return 密钥
     */
    private SecretKey generalSecretKey() {
        byte[] secretBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretBytes, 0, secretBytes.length, "AES");
    }

    /**
     * 签发JWT，创建 token
     *
     * @param userDetails 用户详细信息
     * @return token，token是一次性的。是为一个用户的有效登录周期准备的一个token。用户退出或超时，token失效。
     */
    public String generalToken(UserDetails userDetails) {
        return generalToken(userDetails.getUsername());
    }

    private String generalToken(String account) {
        long createdMillis = System.currentTimeMillis();
        Date createdTime = new Date(createdMillis);
        SecretKey secretKey = generalSecretKey();
        Date expireTime = generalExpireTime(createdMillis);
        JwtBuilder builder = Jwts.builder()
                // 身份标识，具有唯一性
                .setId(account)
                // 签发人
                .setIssuer(account)
                // 存储的用户信息
                .setSubject(account)
                // 生成时间
                .setIssuedAt(createdTime)
                // 过期时间
                .setExpiration(expireTime)
                // 设置密钥及加密算法
                .signWith(signatureAlgorithm, secretKey);
        return builder.compact();
    }

    /**
     * 生成过期时间
     *
     * @param createdMillis 创建时的时间戳
     * @return 过期时间
     */
    private Date generalExpireTime(long createdMillis) {
        Long expire = jwtProperties.getExpire();
        if (expire < 0) {
            throw new IllegalArgumentException("Token的过期时间不能小于0");
        }
        long expireMillis = createdMillis + expire * 1000;
        return new Date(expireMillis);
    }

    /**
     * 解析 token，返回用户信息
     *
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        JWTResult jwtResult = parseToken(token);
        if (jwtResult.success) {
            return jwtResult.claims.getSubject();
        }
        return null;
    }

    private JWTResult parseToken(String token) {
        JWTResult jwtResult = new JWTResult();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(generalSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            jwtResult.success = true;
            jwtResult.claims = claims;
        } catch (ExpiredJwtException e) {
            log.warn("jwt token已过期", e);
            jwtResult.success = false;
            jwtResult.errCode = JWT_ERR_CODE_EXPIRE;
        } catch (Exception e) {
            log.warn("jwt格式验证失败", e);
            jwtResult.success = false;
            jwtResult.errCode = JWT_ERR_CODE_FAIL;
        }
        return jwtResult;
    }

    /**
     * 验证 token 是否合理
     *
     * @return boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        JWTResult jwtResult = parseToken(token);
        return jwtResult.success && userDetails.getUsername().equals(jwtResult.claims.getSubject());
    }

    /**
     * 刷新 token
     *
     * @return 新的 token
     */
    public String refreshToken(String token) {
        JWTResult jwtResult = parseToken(token);
        if (!jwtResult.success) {
            if (jwtResult.errCode == JWT_ERR_CODE_EXPIRE) {
                log.warn("jwt token已过期，无法刷新！");
            } else if (jwtResult.errCode == JWT_ERR_CODE_FAIL) {
                log.warn("jwt格式验证失败，无法刷新！");
            }
            return null;
        }
        Claims claims = jwtResult.claims;
        return generalToken(claims.getId());
    }

    private static class JWTResult {

        private int errCode;

        private boolean success;

        private Claims claims;

    }
}
