package cn.hc.xiaosi;

import org.mybatis.spring.annotation.MapperScan;
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
        System.out.println("程序服务器启动...");
        SpringApplication.run(StartApp.class);
    }

}
