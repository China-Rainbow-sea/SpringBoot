package com.rainbowsea.myspringboot;


import org.apache.catalina.startup.Tomcat;

public class MySpringApplication {

    // 这里我们会创建一个tomcat对象，并关联Spring容器，并启动
    public static void run() {

        try {
            // 创建tomcat对象
            Tomcat tomcat = new Tomcat();

            // 1. 让tomcat 可以将请求转发到spring web 容器，因此需要进行关联
            // 2. “/myspboot 就是我们的项目的 application context ,就是我们原来配置tomcat时，指定application
            tomcat.addWebapp("/app","E:\\Java\\SpringBoot\\quickstart\\mySpringBoot");

            //
            // 设置监视端口为 9090
            tomcat.setPort(8080);

            // 启动 Tomcat
            tomcat.start();

            // 等待请求接入
            System.out.println("===9090===等待请求=========");
            tomcat.getServer().await();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
