# 将 Spring Boot 和 MyBatis 整合
```
mysql

CREATE DATABASE `springboot_mybatis`
USE `springboot_mybatis`

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
VALUES (1,20,'2000-10-10','nmw@sohu.com','男','牛魔王',9000.99)
INSERT INTO  `monster` (`id`,`age` ,`birthday`,`email`,`gender`,`name`,`salary`)
VALUES (2,10,'2000-12-12','bgj@sohu.com','女','白骨精',9999.99)





```


# 切换数据源为 druid

这里我们使用配置类的方式，切换数据源




在类路径下创建mybatis-config.xml
```
yaml

# 老师说明: 配置mybatis的两种方式的选择: 如果配置比较简单，就直接在application.yaml配置
# 如配置内部比较多，可以考虑单独的做一个mybatis-config.xml```
```


```
xml
<typeAliases>
    <package name="com.rainbowsea.springboot.mybatis.bean">
</typeAliases>

```