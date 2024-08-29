package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 表示一个配置类：充当 Spring 配置文件/容器
// 如果该配置类，如果在spring boot 扫描的包下/子包下，会被注入到Spring容器
// 在该类中，可以通过@Bean类注入其它的组件
public class Config {


    /**
     * 1. 通过 @Bean的方式，将new出来的bean对象，放入到 Spring ioc容器当中
     * 2. 该bean在Spring的容器的 name 就是方法名
     * 3. 通过方法名，可以得到 new Dog() 对象，也就是得到注入到 spring ioc 容器中的dog对象
     * @return
     */
    @Bean
    public Dog dog(){
        return new Dog();
    }
}
