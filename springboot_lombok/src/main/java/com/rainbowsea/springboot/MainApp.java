package com.rainbowsea.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication  // 表示启动程序
public class MainApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApp.class, args);

    }

}
