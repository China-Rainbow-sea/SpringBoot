# 内置Tomcat配置和切换

1. Spring Boot 支持的 webServlet: Tomcat，Jetty，or Undertow

2. Spring Boot 应用启动的是 Web 应用时，web场景包-导入 tomcat

3. 支持 Tomcat (也可以是Jetty，Undertow)的配置和切换


## 内置Tomcat的配置

### 第一种方式：通过 application.yaml完成配置
配置和 ServerProperties.java 关联，通过查看源码得知有哪些属性配置（注意：是在类路径下配置该 application.yaml才行）




### 第二种方式：通过类来配置 Tomcat

```
java

package com.rainbowsea.springboot.config;




import org.springframework.boot.web.embedded.jetty.ConfigurableJettyWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * 第二种方式：通过类来配置 Tomcat
 */

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory serverFactory) {
        serverFactory.setPort(9090); // 我们设置了 server的端口为 9090
    }
}

```

## 切换 WebServer, 演示如何切换成Undertow的配置

首先：我们需要排除掉，也就是去掉我们 Spring Boot 内置的 Tomcat 
在配上，导入我们的 Undertow服务器/容器,在 pom.xml 文件上操作

```
xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
<!--            排斥 tomcat starter-->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

```
可能移除 tomcat 后，你的有些内容会爆红，这是正常的，因为最开始的时候引入的 tomcat 里面的
依赖jar包，进行开发的，你只需要将，哪些是用tomcat的jar的类/内容，注释掉即可。

移除之后，导入undertow ，注意刷新，让maven重新加载，然后运行测试
```
xml
<!--        引入 undertow 容器/服务器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-undertow</artifactId>
        </dependency>

```

2024-09-09 17:03:12.631  INFO 20684 --- [           main] o.s.b.w.e.undertow.UndertowWebServer     : Undertow started on port(s) 9090 (http)