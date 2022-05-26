package com.whoai.blog.sso.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 提供容器内对象获取工具类
 *
 * @author xiaosi
 * @date 2022/5/24
 * @since 1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static <T> T getBean(Class<T> tClass) {
        return APPLICATION_CONTEXT.getBean(tClass);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        return APPLICATION_CONTEXT.getBean(name, tClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
}
