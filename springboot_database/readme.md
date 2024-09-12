# 数据库操作

## JDBC + HikariDataSource(Spring Boot内置的数据库)
> 需求：演示 Spring Boot 如何通过 jdbc + HikariDataSource 完成对 MySQL操作：
>说明： HikariDataSource: 目前市面上非常优秀的数据源，是 Spring Boot2默认数据源
>
>
创建的数据库
```
sql

# 创建 furns_ssm
DROP DATABASE if  EXISTS spring_boot
CREATE DATABASE spring_boot

USE spring_boot 

# 创建家居表 数据表
CREATE TABLE furn (
id INT(11) PRIMARY KEY auto_increment, -- id
name VARCHAR(64) not NULL, -- 家具名
maker VARCHAR(64) not null,  -- 厂商
`price` DECIMAL(11,2) not null, -- 价格
`sales` INT(11) not null,  -- 销量
`stock` INT(11) not null,  -- 库存
`img_path` VARCHAR(256) not null  -- 照片路径
  -- 注意：不是单引号
);

SELECT * from  furn;


INSERT into furn(`id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path`)
VALUES(null,'北欧风格小桌子','熊猫家居',100,666,7,'assets/images/producth')
INSERT into furn(`id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path`)
VALUES(null,'简约风格小椅子','熊猫家居',200,666,7,'assets/images/producth')
INSERT into furn(`id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path`)
VALUES(null,'典雅风格小桌子','蚂蚁家居',100,666,7,'assets/images/producth')
INSERT into furn(`id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path`)
VALUES(null,'温馨风格小桌子','蚂蚁家居',100,666,7,'assets/images/producth')

```

在项目当中导入相关的 jar 依赖
```
xml
<!--        进行数据库开发，引入 data-jdbc starter  spring boot 自带的数据库连接池
HikariDataSource-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>

```


```
yaml
String jdbcUrl = "jdbc:mysql://localhost:3306/spring_boot?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
Connection conn = DriverManager.getConnection(jdbcUrl, "username", "password");

```


```
xml


<!--
        1， 引入mysql 驱动，这里我们引入的是 8.0.26
        2. 这个mysql驱动的版本要和实际安装的mysql版本一致
        3. 我们的spring-boot mysql 区别仲裁版本是   <mysql.version>8.0.26</mysql.version>
        4. 这个mysql 驱动的版本，也可以在pom.xml properties 配置文件当中指定。
-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
<!--            <version>8.0.26</version>-->
        </dependency>

```


测试：注意事项：需要导入相关的 jar 包，
```
xml


<!--        如何开发springboot 测试类，我们需要引入  spring-boot-starter-test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

```

```
java
package com.rainbowsea.springboot;


import com.rainbowsea.springboot.bean.Furn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest    // 该注解表示 spring boot 测试
public class ApplicationTests {

    // 大家可以回忆一下，在学习 spring 时，讲过的 JdbcTemplate
    @Resource  //Resource  注解默认根据名称装配byName，未指定name时，使用属性名作为 name，通过name 找不到的话会自动启动通过类型byType装配。
    private JdbcTemplate jdbcTemplate;


    @Test
    public void contextLoads() {
        BeanPropertyRowMapper<Furn> rowMapper = new BeanPropertyRowMapper<>(Furn.class);
        List<Furn> furns = jdbcTemplate.query("select * from furn", rowMapper);

        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }
}

```



```
yaml

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring_boot?useSSL=true&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: MySQL123
    driver-class-name: com.mysql.cj.jdbc.Driver


```
注意：在 spring boot 当中测试的话，必须一定，要编写场景启动器，不然，报错
```
java

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
        
    }
}


```
## 整合  Druid 到 Spring-Boot 

