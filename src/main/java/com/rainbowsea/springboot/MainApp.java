package com.rainbowsea.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


// @SpringBootApplication 表示这是一个springBoot 应用/项目
// @SpringBootApplication(scanBasePackages = {"com.rainbowsea"}) 表示 scanBasePackages = {"com.rainbowsea"} 指定
// springBoot 要扫描的包路径
// 如果有多个包：因为scanBasePackages属性类型是一个数组所以，可以 scanBasePackages = {"com.rainbowsea","xxx.xxx.x"}
@SpringBootApplication(scanBasePackages = {"com.rainbowsea"})
public class MainApp {
    public static void main(String[] args) {
        // 启动springboot应用程序/项目
        //SpringApplication.run(MainApp.class,args);


        // 启动SpringBoot 应用程序/项目
        // 这里使用接口来对接上
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApp.class, args);

        // 如何查看容器中注入的组件
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName --" + beanDefinitionName);
        }
    }
}


/*
 * 1. SpringBoot 内置了Tomcat，简化服务器的设置，
 * 2. 当然 SpringBoot 还有很多优势，后面继续说明
 *
 * */