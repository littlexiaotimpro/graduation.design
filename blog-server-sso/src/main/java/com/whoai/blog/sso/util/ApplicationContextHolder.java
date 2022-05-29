package com.whoai.blog.sso.util;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static Object getBean(String name) {
        try {
            return APPLICATION_CONTEXT.getBean(name);
        } catch (BeansException e) {
            log.warn("cannot get bean [name: {}]", name);
            return null;
        }
    }

    public static <T> T getBean(Class<T> tClass) {
        try {
            return APPLICATION_CONTEXT.getBean(tClass);
        } catch (BeansException e) {
            log.warn("cannot get bean [class: {}]", tClass);
            return null;
        }
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        try {
            return APPLICATION_CONTEXT.getBean(name, tClass);
        } catch (BeansException e) {
            log.warn("cannot get bean [name: {}, class: {}]", name, tClass.getSimpleName());
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
}
