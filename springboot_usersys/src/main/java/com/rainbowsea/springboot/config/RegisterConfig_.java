package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.servlet.Filter_;
import com.rainbowsea.springboot.servlet.Listener_;
import com.rainbowsea.springboot.servlet.Servlet_;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

// 使用  配置类的方式注入，servlet，和 Listener监听器，filter过滤器

/*
 * @Configuration(proxyBeanMethods = true)
 * @Configuration 表示是一个配置类
 * proxyBeanMethods = true 默认是单例返回 bean(保证每个 @bean 方法被调用多少次，都是同一个)

*/
@Configuration(proxyBeanMethods = true)
public class RegisterConfig_ {


    // 注入: Listener
    //@Bean(name = "Listener_")
    @Bean
    public ServletListenerRegistrationBean Listener2() {
// 创建原生的 Listener_ 对象（就是我们自己创建的 Listener_）
        Listener_ listener_ = new Listener_();

        return new ServletListenerRegistrationBean(listener_);

    }
}
