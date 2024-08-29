package com.rainbowsea.myspringboot;

import com.rainbowsea.myspringboot.config.MyConfig;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * 解读:
 * 1. 创建我们的Spring容器
 * 2. 加载/关联Spring容器的配置-按照注解的方式
 * 3. 完成Spring容器配置的bean的创建，依赖注入
 * 4. 创建前端控制器 DispatcherServlet，并让其持有Spring容器
 * 5. 当DispatcherServlet 持有容器，就可以进行分发映射，请小伙伴回忆我们实现SpringMVC
 * 6. 这里onStartup 是Tomcat 调用，并把ServletContext 对象传入
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("startup...");
        // 加载Spring web application configuration => 容器


        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();

        ac.register(MyConfig.class);
        ac.refresh();  // 完成bean的创建和配置

        // 1. 创建注册非常重要的前端控制器 当DispatcherServlet
        // 2. 让 DispatcherServlet 持有容器
        // 3. 这样就可以进行映射分发，回忆一下 SpringMVC 机制（自己实现过）
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);

        // 返回  ServletRegistration.Dynamic 对象
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);

        // tomcat 启动时，加载  dispatcherServlet
        registration.setLoadOnStartup(1);

        // 拦截请求，并进行分发处理
        // 这里老师在提示  "/" 和 “/*”  在老师讲解 java web
        registration.addMapping("/");

    }
}
