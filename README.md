# SpringBoot
个人对于SpringBoot 框架上的练习和学习总结


SpringBoot 内置了Tomcat，简化服务器的设置，
当然 SpringBoot 还有很多优势，后面继续说明


# Spring,SpringBoot,SpringMVC 之间的关系

> 1. 它们的关系大概是：SpringBoot(包含了Spring)>Spring>SpringMVC
> 2. SpringMVC 只是Spring 处理Web层请求的一个模块/组件，Spring MVC 的即基石
> 是Servlet
> 3. Spring的核心是IOC和AOP，IOC提供了依赖注入**容器** ，AOP解决了**面向切面编程** 
> 4. Spring Boot 是为了简化开发，推出的**封神框架（约定优于配置（COC），简化了Spring 项目的配置流程）
>SpringBoot 包含很多组件/框架，Spring就是最核心的内容之一，也包含了Spring MVC
> 5.Spring 家族，有众多衍生框架和组件例如：boot,security,jpa等，他们的基础都是Spring


# 理解约定优于配置
1. 约定优于配置（Convention over Configuration / CoC）,又称约定编程，是一种软件设计规范，本质上是对系统，类库或框架中一些东西，
假定一个大众化合理的默认值（缺省值）
2. 例如在模型中存在一个名为User的类，那么对应到数据库会存在一个名为 user 的表，只有偏离这个约定时才需要做相关的配置（例如
你想将表名命名为：t_user等非user时才需要写关于这个名字的配置）
3. 简单来说就是假如你所**期待的配置** 与 **约定的配置** 一致，那么就可以不做任何配置，约定不符合期待时，才需要对约定进行替换配置。

4.约定优于配置：为什么要搞一个“约定优于配置”
约定其实就是一个规范，遵循了规范，那么就存在通用性。存在了通用性，那么事情就会变得**相对简单** ，程序员之间的沟通成本就会降低，
工作效率会提升，合作也会变得更加简单。
--：生活中，这样的情况，大量存在：比如：两个都是男生，你说一起去上厕所去，那么，他们两个人都是（默认，约定好的都是去男生厕所，而不用说是：去女生厕所。）
注意：误区：默认是采用约定的，但是你配置了，还是采用你的配置的信息的，




# starter 场景启动器基本介绍

1. 开发中我们引入相关的场景starter（启动器），这个场景中所有的相关依赖都引入进来了，比如我们做web开发引入了
该starter将导入web开发相关的所有包


# 第三方 starter   启动器
1.SpringBoot 也支持第三方starter
2.第三方starter不要从spring-boot 开始，因为这是官方spring-boot保留的命名方式的。
第三方启动程序通常以项目名称开头，例如：名“XXx”的第三方启动程序项目通常被命名为：“XXx-spring-boot-stater”
3.也就是说：xxx-spring-boot-starter是第三方为我们提供的简化开发的场景启动器



# 自动配置介绍：

1.大家是否还记得，前面学习SSM整合时，需要配置Tomcat，配置SpringMVC,配置如何扫描包，配置字符过滤器，配置
视图解析器，文件上传等（如图），非常麻烦，而在SpringBoot当中，存在“自动配置机制”，提高开发效率
2.简单的回顾之前SSM整合的配置。


# Spring Boot 的包结构

https://docs.spring.io/spring-boot/reference/using/structuring-your-code.html#using.structuring-your-code.using-the-default-package

com
 +- example
     +- myapplication
         +- MyApplication.java
         |
         +- customer
         |   +- Customer.java
         |   +- CustomerController.java
         |   +- CustomerService.java
         |   +- CustomerRepository.java
         |
         +- order
             +- Order.java
             +- OrderController.java
             +- OrderService.java
             +- OrderRepository.java
             
             
  
 
 

# Spring Boot 中的 application.properties 配置
SpringBoot 项目中最重要也是最核心的配置文件就是application.properties,所有的框架配置都可以在这个配置文件中说明进行配置相关信息。
 Spring Boot 配置大全: https://blog.csdn.net/pbrlovejava/article/details/82659702
 
 
 
# 修改配置
各种配置都有“默认”，可以在 resources\application.properties 修改，application.properties文件我们可以手动创建
注意：命名必须是“application.properties” 不可以为其它的。


# application.properties 常用配置



# application.properties 自定义配置


# springboot 自动配置
1. 自动配置遵守按需加载原则：也就是说，引入了哪个场景 starter 就会加载该场景关联的jar包，没有引入的 starter 则不会加载其关联 jar
2. SpringBoot 所有的自动配置功能都在 spring-boot-autoconfigure 包里面
3. 在Spring Boot 的自动配置包，一般SHI XxxAutoConfiguration.java 与之对应一个 XxxProperties.java,
org.springframework.boot.autoconfigure.amqp
org.springframework.boot.autoconfigure.amqp.RabbitProperties
 org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration 