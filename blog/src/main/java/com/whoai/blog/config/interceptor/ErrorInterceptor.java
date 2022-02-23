package com.whoai.blog.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ErrorInterceptor implements HandlerInterceptor {

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("请求处理之前进行调用（Controller方法调用之前）");
        }
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
        }
//        if (response.getStatus() == 404) {
//            /*
//             * 请求执行并处理提交后，此时不能进行重定向设置
//             * 在出现请求访问失败之后，已经执行了重定向至 /error
//             * 此时再次执行重定向会报错
//             * java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
//             */
//            response.sendRedirect("/404");
//        }
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (log.isDebugEnabled()) {
            log.info("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
        }
    }
}
