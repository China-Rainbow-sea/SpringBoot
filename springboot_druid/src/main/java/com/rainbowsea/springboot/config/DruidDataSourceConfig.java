package com.rainbowsea.springboot.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;


@Configuration  // 标注配置类
public class DruidDataSourceConfig {

    // 编写方法，注入 DruidDataSource

    @Bean // 注入到 ioc 容器当中，没有指明名字，默认方法名就是 id/名称
    // 老师还有一个说明为什么我们注入自己DataSource,默认的HirKarDatasource失效的
    @ConfigurationProperties(value = "spring.datasource") // 该注解，让 application.yaml
    // 当中配置的信息，根据其后缀，将其配置到该类的属性 set()方法当中进行一个赋值操作。
    // 注入到 ioc 容器当中。
    public DataSource dataSource() throws SQLException {

        // 1.配置了 @ConfigurationProperties("spring.datasource")
        // 就可以读取到application.yaml的配置
        // 2.我们就不需要调用DruidDataSource 对象的setXX,会自动关联


        DruidDataSource druidDataSource = new DruidDataSource();
        //druidDataSource.setUrl();
        //druidDataSource.setUsername();
        //druidDataSource.setPassword();


        // 将数据库连接池加入到 druid监视当中
        // wall ，将数据库连接池加入sql防火墙
        druidDataSource.setFilters("stat,wall");

        return druidDataSource;
    }


    // 配置druid的监控页功能, 注意：是 Servlet 注入的配置，和上次内容的 自定义 Servlet的注入
    @Bean // 注意要注入到 ioc 容器当中
    public ServletRegistrationBean staatViewServlet() {
        // 创建 StatViewServlet
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet>
                statViewServletServletRegistrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        // statViewServlet, "/druid/*") 表示映射的路径

        // 设置 init-parameter,就是设置进入 Druid的账号和密码;
        statViewServletServletRegistrationBean.addInitParameter("loginUsername", "rainbowsea");
        statViewServletServletRegistrationBean.addInitParameter("loginPassword", "666");


        return statViewServletServletRegistrationBean;


    }


    // 配置 WebStatFilter，用于采集 web-jdbc 关联的监控数据
    // 注意用的是 Filter 过滤器
    @Bean
    public FilterRegistrationBean webStatFilter() {

        // 创建 WebStatFilter ,
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> webStatFilterFilterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);

        // 默认对所有的url请求进行监控,
        // 表示过滤所有请求，Arrays.asList("/*") 将字符串转换为集合
        webStatFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        // 排除指定的url
        webStatFilterFilterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");


        return webStatFilterFilterRegistrationBean;

        /*
          <filter>
  	<filter-name>DruidWebStatFilter</filter-name>
  	<filter-class>com.alibaba.druid.support.http.WebStatFilter</filter-class>
  	<init-param>
  		<param-name>exclusions</param-name>
  		<param-value>*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*</param-value>
  	</init-param>
  </filter>
  <filter-mapping>
  	<filter-name>DruidWebStatFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
         */

    }
}
