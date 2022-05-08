package com.whoai.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan({"com.whoai.blog.dao","com.whoai.blog.sso.dao"})
@EnableFeignClients("com.whoai.blog.feign")
@EnableDiscoveryClient
public class StartApp {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(StartApp.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}
