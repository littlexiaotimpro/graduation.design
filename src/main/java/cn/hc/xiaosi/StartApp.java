package cn.hc.xiaosi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName StartApp
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/1314:19
 */
@SpringBootApplication
@MapperScan("cn.hc.xiaosi.dao")
public class StartApp {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(StartApp.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}
