package com.rainbowsea.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication // 项目启动标志
@ServletComponentScan(basePackages = {"com.rainbowsea.springboot"})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
        //ioc.stop();  // 停止容器

        System.out.println("hello");
    }
}
