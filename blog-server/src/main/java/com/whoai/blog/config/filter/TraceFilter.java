package com.whoai.blog.config.filter;

import com.whoai.blog.util.TraceUtil;
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
 * 为每一个请求设置一个唯一的 traceID
 */
@Slf4j
@Component
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 为每一个请求设置一个 traceId
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
