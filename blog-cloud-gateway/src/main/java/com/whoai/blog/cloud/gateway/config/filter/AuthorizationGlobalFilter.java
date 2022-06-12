package com.whoai.blog.cloud.gateway.config.filter;

import cn.hutool.core.util.StrUtil;
import com.whoai.blog.jwt.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 权限认证全局过滤器
 * <p>
 * 1.若未授权则不允许访问
 * <p>
 * 2.已授权但权限不对，不允许访问（待定）
 *
 * @author xiaosi
 * @date 2022/5/30
 * @since 1.0
 */
@Slf4j
@Component
public class AuthorizationGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (log.isInfoEnabled()) {
            log.info("AuthorizationGlobalFilter filter url: {}", request.getURI().getPath());
        }
        ServerHttpResponse response = exchange.getResponse();
        String authorization = response.getHeaders().getFirst(jwtProperties.getTokenHeader());
        // token不存在 | 不是指定格式的token，无法通过认证
        if (StrUtil.isBlank(authorization) || !StrUtil.startWith(authorization, jwtProperties.getTokenHead())) {
            response.setStatusCode(HttpStatus.FOUND);
            response.getHeaders().set(HttpHeaders.LOCATION, "https://cn.bing.com/");
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
