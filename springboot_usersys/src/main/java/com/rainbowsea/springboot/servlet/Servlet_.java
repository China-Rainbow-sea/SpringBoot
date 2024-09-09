package com.rainbowsea.springboot.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 提示: urlPatterns = {"/servlet01","servlet02"},对Servlet配置了url-pat:请求路径的映射
 * 注入的原生的 Servlet_，不会被Spring boot的拦截器拦截
 * 对于开发的原生的Servlet,需要使用@ServletComponentScan指定要扫描的原生Servlet,才会注入到 Spring容器当中
 * 注意：是在启动场景的位置添加该注解
 *
  @ServletComponentScan(basePackages = {"com.rianbowsea.springboot"})  // 开启包扫描。指明要
 // 扫描的 servlet的包位置。
  @SpringBootApplication // 项目启动标志
 public class Application {

 public static void main(String[] args) {
 SpringApplication.run(Application.class,args);

 System.out.println("hello");
 }
 }


 */

// 使用 extends 继承的方式(@WebServlet + @ServletComponentScan 注解)，注入 servelt
// 先注释掉，使用第二种方式： @WebServlet(urlPatterns = {"/servlet01","/servlet02"}) // 注意是： / 开头
public class Servlet_ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException,
            IOException {
        // 在前端显示打印显示一些信息。
        response.getWriter().write("hello , Servlet_!");

    }
}
