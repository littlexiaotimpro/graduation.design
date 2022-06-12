package com.whoai.blog.cloud.gateway.controller;

import com.whoai.blog.bean.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

/**
 * 服务降级或熔断的默认接口
 */
@RestController
@Slf4j
public class FallBackController {

    @GetMapping(value = "/fallback")
    public ResponseResult<String> fallback(ServerWebExchange exchange) {
        // 依据配置文件使用的熔断方式，获取异常信息,Hystrix、CircuitBreaker
        Exception hystrixException = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        Exception circuitBreakerException = exchange.getAttribute(ServerWebExchangeUtils.CIRCUITBREAKER_EXECUTION_EXCEPTION_ATTR);
        ServerWebExchange delegate = ((ServerWebExchangeDecorator) exchange).getDelegate();
        log.error("URL【{}】调用失败", delegate.getRequest().getURI());
        log.error("hystrixException", hystrixException);
        log.error("circuitBreakerException", circuitBreakerException);
        return ResponseResult.success("fallback", "cloud-base-gateway: 服务故障，请稍后重试！");
    }

}
