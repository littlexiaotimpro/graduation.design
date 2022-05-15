package com.whoai.blog.jwt;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(JwtConfiguration.class)
public @interface EnableJwt {
}
