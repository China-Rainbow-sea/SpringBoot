# 整合 Druid 数据库连接池 到 Spring Boot 当中


## 官方文档
https://github.com/alibaba/druid

https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98

## 基本介绍

HiKariCP: 目前市场上非常优秀的数据源，是 spring Boot2 默认数据源
Druid:性能优秀，Druid提供的监控功能外{Java基础}，还集成了SQL监控。可以清楚知道连接池和 SQL的工作情况
所以根据项目需要，我们也要掌握Druid和SpringBoot整合
Druid到 Spring-Boot方式
两种方式：
1.自定义方式
2. 引入 starter 方式




## Druid 的基本使用


```
xml
<!--        引入 druid 依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.8</version>
        </dependency>

```
DataSourceAutoConfiguration.java 类

```
java
    @Conditional({DataSourceAutoConfiguration.PooledDataSourceCondition.class})
    @ConditionalOnMissingBean({DataSource.class, XADataSource.class})
    @Import({Hikari.class, Tomcat.class, Dbcp2.class, OracleUcp.class, Generic.class, DataSourceJmxConfiguration.class})
    protected static class PooledDataSourceConfiguration {
        protected PooledDataSourceConfiguration() {
        }
    }


```
 @ConditionalOnMissingBean({DataSource.class, XADataSource.class}) // 默认的数据源是如配置？
 解读通过： @ConditionalOnMissingBean({DataSource.class, XADataSource.class}) 判断如果容器有DataSource Bean
 就不注入HiKariDatasource
 
 
## Druid 监控功能 -SQL监控 
1.第一种方式是：在 web.xml 当中配置，需要在你web应用中的WEB-INF/web.xml中
根据配置中的url-pattern来访问内置监控页面，如果
https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE


根据配置中的url-pattern来访问内置监控页面，如果是上面的配置，内置监控页面的首页是/druid/index.html


   // 将数据库连接池加入到 druid监视当中
        druidDataSource.setFilters("stat");

## Druid 监控功能 - web 关联监控 
https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_%E9%85%8D%E7%BD%AEWebStatFilter

## Druid 监控功能  - SQL防火墙

https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98#8-%E6%80%8E%E4%B9%88%E9%85%8D%E7%BD%AE%E9%98%B2%E5%BE%A1sql%E6%B3%A8%E5%85%A5%E6%94%BB%E5%87%BB

## Druid 监控功能  - WEb应用



## Druid 监控功能 - Session监控等

默认 Session 监视就是开启的


## Druid Spring Boot Starter

前面我们使用的是自己引入druid+配置类方式整合Druid和监控
Druid Spring Boot Starter 可以让程序员在SpringBoot项目中更加轻松集成Druid和监控
