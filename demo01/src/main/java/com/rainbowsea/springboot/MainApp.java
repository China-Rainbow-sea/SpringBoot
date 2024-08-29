package com.rainbowsea.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication  // 标注Spring Boot 也就是应用程序的启动场景
public class MainApp {
    public static void main(String[] args) {

        // 启动Spring Boot 应用程序/项目
        // 老师提出问题：当我们执行 run 方法时，怎么就启动我们内置的tomcat
        // 再分析run方法的底层机制的基础上，我们直接尝试实现
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApp.class, args);

        System.out.println("hello ioc");
    }
}
