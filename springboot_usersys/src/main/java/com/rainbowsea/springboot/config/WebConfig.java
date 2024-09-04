package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 配置拦截器
public class WebConfig
/*        implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器LoginInterceptor
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  // 拦截所有的请求
                .excludePathPatterns("/","/login","/images/**"); // 还要放行视图的内容，因为上面是
        // 拦截所有，注意不要: templates ，因为sprinboot的默认配置，就是以templates为根路径往下找的
        // 所以添加就错了，就成了 /templates/templates/images/**了。

    }*/
{

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
}
