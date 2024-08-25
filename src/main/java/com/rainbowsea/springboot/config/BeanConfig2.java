package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 这是第二个配置类
 */
@Configuration  // 标注是配置类
public class BeanConfig2 {


    @Bean  // bean 默认是以 “方法名”作为 id/名字 ，注入到 ioc容器当中
    public Monster monster02(){
        return new Monster(800,"蚂蚁精",80,"吃小昆虫");
    }

}
