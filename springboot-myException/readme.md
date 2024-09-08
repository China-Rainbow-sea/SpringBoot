# 异常处理


## 介绍
1.默认情况下，Spring Boot 提供 /error 处理所有的错误的映射，也就是说当出现错误时， 
Spring Boot 底层会，请求转发到 /error 这个映射。
注意是：请求转发，不是重定向的。

```
java
1.默认情况下，Spring Boot 提供 /error 处理所有的错误的映射，也就是说当出现错误时， 
Spring Boot 底层会，请求转发到 /error 这个映射。
注意是：请求转发，不是重定向的。
```






# 拦截器 VS 过滤器

1. 使用范围不同
> 1. 过滤器事项的是 javax.servlet.Filter 接口，而这个接口在 Servlet 规范中定义的，也就是说
> 2. 过滤器 Filter 的使用依赖于 Tomcat 等容器，Filter 只能在 web 程序中使用
> 3. 拦截器(Interceptor) 它是一个Spring 组件，并由Spring 容器管理，并不依赖 Tomcat 等容器，是可以
>单独使用的，不仅能应用在 Web 程序中，而可以用于 Application 等程序中。

2. 过滤器 和 拦截器 的触发时机不同，看下边这张图：
过滤器Filter 是在请求进入容器后，但在进入 servlet 之前进行预处理。请求结束是Servlet 处理完以后。
拦截器 Interceptor 是在请求进入 servlet 后，在进入 Controller 控制器之后进行预处理的，Controller 中渲染
了对应的视图之后请求结束

说明：过滤器不会处理请求转发，拦截器会处理请求转发。


3. 说明：过滤器不会处理请求转发，拦截器会处理请求转发。因为过滤器是自己内部进行过滤转发的，
会先找自己同一层的内部的映射，如果没有找到才会走拦截器需要的。
4. 至于过滤器和拦截器的原理和机制，老韩已经详细讲解过了，过滤器在JavaWeb讲过



## 自定义异常

自定义404.html，500.html 4xx.html 5xx.html 当发生相应错误时，显示自定义的页面信息


## 全局异常
@ControllerAdvice + @ExceptionHandler 处理全局异常
底层是 ExceptionHandlerExceptionResolver 支持的
不可以省略其它的。

> **注意：全局异常> 自定义异常>内置异常 的优先级** 

需求: 演示全局异常使用，当发生ArithmeticException,NullPointerException,不使用默认异常的机制
匹配的 xxx.html，而是显示全局异常机制指定的错误页面。所以，全局异常可以说是最高
优先级了。


1. 如果Spring Boot 提供的异常不能满足开发需求，程序员也可以自定义异常。
2. @ResponseStatus + 自定义异常
3. 底层是 ResponseStatusExceptionResolver
4. 当抛出自定义异常后，任然会根据状态码，去匹配使用xxx.html显示
