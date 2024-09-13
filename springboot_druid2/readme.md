# 如何使用  Druid Spring Boot Starter

https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter


```
xml
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>druid-spring-boot-starter</artifactId>
   <version>1.1.17</version>
</dependency>
```


https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter

elelctAllow	true	是否允许执行SELECT语句
selectAllColumnAllow	true	是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。


  select-all-column-allow: false
  
  java.sql.SQLException: sql injection violation, 'SELECT *' not allow : select * from furn
  