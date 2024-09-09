# thymeleaf 的实例

```
java

@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML";
    private Charset encoding;
```

注意：路径不可以随便改，要按照要求来，或者通过 application.yaml 进行修改


# 拦截器
1. 在Spring Boot项目中，拦截器是开发中常用手段，要来做登录验证，性能检查，日志记录等。
**2种方式：** 
基本步骤：
编写一个拦截器实现HandlerInterceptor接口
拦截器注册到配置类中(实现WebMvcConfigurer的addInterceptors)
指定拦截规则
回顾SpringMVC中讲解的Interceptor

## 第一种方式，使用配置类，配置拦截器

```
java
package com.rainbowsea.springboot.controller;


import com.rainbowsea.springboot.bean.Admin;
import com.rainbowsea.springboot.bean.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AdminController {


    @PostMapping("/login")
    public String login(Admin admin, HttpSession session, Model model) {




        // 验证用户是否合法
        if(StringUtils.hasText(admin.getName()) && "666".equals(admin.getPassword())) {
            // 将登录用户保险到 session会话域当中
            session.setAttribute("loginAdmin",admin);

            // 合法，重定向到manage.html
            // 请小伙伴回忆，Java web ，不使用请求转发是防止刷新页面会重复提交
            // 这里老师为什么写的是 manage.html，因为这样可以更加明确的表示到哪个页面
            // manage.html 表示要去找方法的映射路径为: manage.html
            return "redirect:/manage.html";
        } else {
            // 不合法，就重新登录，请求转发
            model.addAttribute("msg","账号/用户错误");
            return "adminLogin";  // 视图解析
        }

    }

    // 处理用户的请求到 manage.html
    // 是重定向——>get
    @GetMapping("/manage.html")
    public String mainPage(Model model,HttpSession session){

        // 可以这里集合~模板数据，放入到request域中，并显示
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(2,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(3,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(4,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(5,"关羽","666",28,"gy@ohu.com"));
        model.addAttribute("users",users);  // 放入到请求域当中
        return "manage";  // 视图解析器

       
    }
}

```

```
java
package com.rainbowsea.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 为了让大家看到访问的URI
        String requestURI = request.getRequestURI();
        log.info("preHandle 拦截到的请求URI={}", requestURI);

        // 进行登录的校验
        HttpSession session = request.getSession();
        Object loginAdmin = session.getAttribute("loginAdmin");
        if (null != loginAdmin) { // 说明该用户已经成功登录
            // 返回 true 就是放行
            return true;
        } else {
            // 拦截，重新返回到登录页面
            request.setAttribute("msg", "你没有登录/请登录~~~");
            // 注意：因为这里我们只有一个内容被拦截了，而且该内容的 uri路径就是我们要跳转进入的路径
            request.getRequestDispatcher("/").forward(request, response);  // 重定向
            return false; // 拦截了，不放行

        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle执行了...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion执行了...");

    }
}
// HandlerInterceptor
```
```
java
package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 配置拦截器
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器LoginInterceptor
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  // 拦截所有的请求
                .excludePathPatterns("/","/login","/images/**"); // 还要放行视图的内容，因为上面是
        // 拦截所有，注意不要: templates ，因为sprinboot的默认配置，就是以templates为根路径往下找的
        // 所以添加就错了，就成了 /templates/templates/images/**了。

    }
}

```

1.URI 和 URL 的区别
URI = Universal Resource Identifier
URL = Universal Resource Locator

```
java
  // 为了让大家看到访问的URI
        String requestURI = request.getRequestURI(); // preHandle 拦截到的请求URI=/manage.html
        String requestURL = request.getRequestURL().toString();  // preHandle 拦截到的请求URL=http://localhost:8080/manage.html

```

## 拦截器的第二种方式：
基于第一种方式修改，webconfig 配置类即可
```
java
package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 配置拦截器
public class WebConfig{

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

```

**注意：配置了拦截器的话，图片之类的一些静态资源的访问，也是要注意放行的，已经视图解析器也是放行的** 
要放行视图的内容，因为上面是
        // 拦截所有，注意不要: templates ，因为sprinboot的默认配置，就是以templates为根路径往下找的
        // 所以添加就错了，就成了 /templates/templates/images



# 自定义异常
1. 需求: 自定义一个异常AccessException，当用户访问某个无权访问的路径时，抛出该异常，显示自定义异常状态

执行效果如图：浏览器: http://locathos:8080/err3
> **简单的说：其实就是我们在 java se 当中学习到自定义异常处理** 


如果把自定义异常类型，放在全局异常回来，那么仍然走全局异常处理机制。
简单的说：我们自定义的异常类型，也是可以放到全局异常当中的。





# 注入 Servlet，Filter，Listener

## 基本介绍
1.考虑到实际开发业务非常复杂和兼容，Spring-Boot 支持将 Servlet，Filter ，Listener注入Spring容器
，成为Spring bean
2.也就是说明 Spring Boot 开放了和原生 WEB组件(Servlet,Filter,Listener)的兼容。


# 使用 RegistrationBean 方式注入 Servlet,Filter过滤器，Listener拦截器


# 注意事项和细节说明:


**请求 (自己所编写的)Servlet 时，为什么不会到达拦截器** 
> 请求 Servlet 时，不会到达 DispatherServlet,因此也不会到达拦截器
>原因分析:
>   注入的Servlet会存在Spring容器
>   DispatherServlet也存在Spring 容器
>多个Servlet容器能处理到同一层拦截，精确优先原则/最长前缀匹配原则
>所以当请求/servlet01时，就会直接匹配到注入的servlet
>简单的说：就是当你 servlet之间跳转通信的时候，是先找同一层的servlet，如果你同一层的
>servlet有你所需要的映射的请求路径，那么优先跳转到servlet上，而不走 拦截器了，因为拦截器是在
>介于 servlet 和 Controller 控制器之间的。
大家可以回忆一下：我们讲过的 Tomcat在对 Servlet url 匹配的原则，多个servlet都能处理到同一层路径
>，精确优先原则/最长前缀匹配原则
在Spring Boot 中，去调用@Controller 目标方法，是按照DispatherServlet分发匹配的机制，请同学们
>回顾一下，我们自己实现Spring MVC 的底层机制的程序