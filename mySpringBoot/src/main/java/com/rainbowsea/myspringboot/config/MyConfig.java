package com.rainbowsea.myspringboot.config;


import com.rainbowsea.myspringboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/*
这里有一个问题，容器怎么知道要扫描哪些包？
在配置类可以指定要扫描包: @ComponentScan("com.rainbowsea.myspringboot")
MyConfig 配置类-作为Spring的配置文件
 */
@ComponentScan("com.rainbowsea.myspringboot.bean")
@Configuration  // 标注: 设置类
public class MyConfig {

    // 注入Bean - monster 对象到 Spring 容器
    @Bean
    public Monster monster() {
        return new Monster();
    }

}
