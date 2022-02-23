package com.whoai.blog.config.filter;

import com.whoai.blog.utils.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求拦截器（在进入 Controller 前），可以过滤请求或设置一些参数
 * <p>
 * TODO 如下（可以由前端解决）：
 *  - 用户未登录时，在一些需要登录的操作上进行过滤
 *  - 用户登录后，缓存用户信息，在一些需要权限的操作上进行过滤
 */
@Slf4j
@Component
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 设置响应参数
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try {
            MDC.put(TraceUtil.TRACE_ID, TraceUtil.getTraceId());
            if (log.isDebugEnabled()) {
                log.debug("当前请求【{}】", request.getRequestURL());
            }
            //此行代码确保请求可以继续执行至Controller
            filterChain.doFilter(request, response);
        } finally {
            TraceUtil.removeTraceId();
        }
    }
}
