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

```java

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
             
             
```
  
 
 

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
 
# Spring Boot 容器功能
> 说明: 这些在Spring 中的传统注解仍然有效，通过这些注解可以给容器注入组件: Spring中传统的注解依然可以使用 @Controller,@Service, @Repository,@Component
>
>
>




## Spring 注入组件的注解

## @Configuration

@Configuration 注意事项和细节： 自定义配置类的
> 1. proxyBeanMethods : 代理bean的方法
> 2. Full(proxyBeanMethods = true) 单例：保证每个@Bean方法被调用多少次返回的组件都是单实例的，是代理方式，是共用的
> 3. Lite(proxyBeanMethods = false) 多例： 每个@Bean方法被调用多少次，返回的组件都是新创建的，是非代理方式 ，非共用的
>特别说明：proxyBeanMethods 是在调用 @Bean方法才生效，因此，需要先获取到对应 BeanConfig(配置类的组件)，再从配置类组件的方法
>中，调用方法，获取到的Bean，而不是直接通过 SpringBoot 主程序的 ioc,getBean()直接得到容器来获取 bean,
>注意观察直接通过ioc.getBean()获取 Bean, proxyBeanMethods 值并没有生效。
> 4 . 如何选择：组件依赖必须使用Full模式(默认就是 full模式)，如果不需要组件依赖使用 Lite模式
> 5. Lite 模式也称之为：“轻量级模式”，因为不检测依赖关系，运行速度快。
> 6. 当然，如果你仅仅只想要其中的一个对象，进行多例的话，就采用，@Scope("prototype")在该返回对象的方法上，添加上该注解即可
> 


## @Import

## @Conditional
1.条件装配: 满足Conditional指定的条件，则进行组件注入
2.@Conditional 是一个根注解，下面有很多扩展注解


## @ImportResource
作用: 原生配置文件引入，也就是可以直接导入 Spring 传统的 beans.xml，
可以认为是SpringBoot 对 Spring 容器文件的兼容
需求: 将 beans.xml 导入到 BeanConfig.java配置类，并测试是否可以获得 beans.xml注入的组件

## 注意事项

## 配置绑定


如果application.properties有中文，需要转成unicode编写写入，否则出现乱码
 
 
 解决：
 > Spring Boot Configuration Annotation Processor not configured 提示信息
>问题：
>解决  @ConfigurationProperties(prefix = "furn01") 提示信息,  在 pom.xml  增加依赖,  即可
 <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-configuration-processor</artifactId> <optional>true</optional>
 </dependency>