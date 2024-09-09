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

/**
 * @Configuration(proxyBeanMethods = true)
 * @Configuration 表示是一个配置类
 * proxyBeanMethods = true 默认是单例返回 bean(保证每个 @bean 方法被调用多少次，都是同一个)
 */
@Configuration(proxyBeanMethods = true)
public class RegisterConfig_ {


    // 以使用RegistrationBean 方式
    // 注入 Servlet
    // 注意：要加上 Bean 对象
    //@Bean(name = "Servlet_") // bean 没有指明name的话，默认是以方法名作为 name/id
    @Bean
    public ServletRegistrationBean servlet2() {
        // 创建原生的 Servlet 对象（就是我们自己创建的 Servlet）
        Servlet_ servlet_ = new Servlet_();

        // 把 Servlet_ 对象 关联到 ServletRegistrationBean 对象
        // "/servlet03" 就是注入Servlet的url-pattern
        return new ServletRegistrationBean(servlet_, "/servlet03");
    }

    // 注入 Filter
    // 注意：要加上 Bean 对象
    @Bean(name = "Filter_")
    public FilterRegistrationBean filter2() {
        // 创建原生的 Filter_ 对象（就是我们自己创建的 Filter_）
        Filter_ filter_ = new Filter_();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter_);
        // 设置 filter 的 url-pattern
        // Arrays.asList("/css/*","images/*") 将字符串，转换为 集合
        // 注意：不要漏 "/" 开头了。
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/css/*", "/images/*"));

        return filterRegistrationBean;
    }

    // 注入: Listener
    //@Bean(name = "Listener_")
    @Bean
    public ServletListenerRegistrationBean Listener2() {
// 创建原生的 Listener_ 对象（就是我们自己创建的 Listener_）
        Listener_ listener_ = new Listener_();

        return new ServletListenerRegistrationBean(listener_);

    }
}
