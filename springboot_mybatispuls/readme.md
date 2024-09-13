# Spring Boot 整合 MyBatis-Plus
官方文档: 

准备数据:
```
sql

CREATE DATABASE `springboot_mybatispuls`

USE `springboot_mybatispuls`


CREATE TABLE `monster` (
`id` int not null auto_increment,
`age` int not null,
`birthday` DATE DEFAULT NULL,
`email` VARCHAR(255) DEFAULT NULL,
`gender` CHAR(1) DEFAULT null,
`name` VARCHAR(255) DEFAULT NULL,
`salary` DOUBLE not NULL,
PRIMARY KEY(`id`)
)

SELECT * from monster



INSERT INTO  `monster` (`id`,`age` ,`birthday`,`email`,`gender`,`name`,`salary`) 
VALUES (1,20,'2000-10-10','nmw@sohu.com','男','牛魔王',9000.99);
INSERT INTO  `monster` (`id`,`age` ,`birthday`,`email`,`gender`,`name`,`salary`)
VALUES (2,10,'2000-12-12','bgj@sohu.com','女','白骨精',9999.99);



```


```
xml

<!--        引入 mybatis-puls starter-->
        <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3</version>
        </dependency>
```

如果这个类名 Monster 和表名不一致，不能映射上，则可以通过@TableName指定
@TableName("monster_") // 填数据库表当中定义的表名