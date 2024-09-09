package com.rainbowsea.springboot.servlet;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


// 注意是:  javax.servlet.Filter 下的 Filter
// 注入过滤器:(使用： @WebFilter(urlPatterns = {"/css/*","/images/*"}) + @ServletComponentScan(basePackages = {"com.rainbowsea.springboot"}))
/*
@WebFilter(urlPatterns = {"/css/*", "/images/*"})
@WebFilter 表示 Filter_是一个过滤器，并注入容器
urlPatterns = {"/css/*", "/images/*"} 当请求 /css/ 目录资源或者images
解读: 直接放行后，在经过拦截器，拦截器是否拦截要根据拦截器的拦截规则

特别说明在：之前下面这样配置的拦截器也是会拦截内容的。
 @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                System.out.println("addInterceptors~~~");
                // 注册拦截器
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/login","/images/**");
            }
        };
    }

 注意：过滤器配置的urlPatterns 也会经过 Spring-Boot拦截器，所以为了
 看到效果，请在拦截器配置放行 /css/**,
 在 servlet 表示全部匹配是 "/*"；而在Spring boot 中表示全部匹配的是: "/**"
 */

@Slf4j
// 先注释掉，使用第二种方式：@WebFilter(urlPatterns = {"/static/css/*", "/images/*"})  // 注意：是/开头
public class Filter_ implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--Filter_ init0--");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter - doFitler");
        // 为了方便观察过滤器处理的资源，我们输出一个url
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        log.info("过滤器处理的 url={}",httpServletRequest.getRequestURI());

        // 我们直接放行,实际开发中，根据自己的业务来决定如何处理
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("Filter -destory");
    }
}
