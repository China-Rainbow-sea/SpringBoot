# SpringBoot 中对于静态资源的访问
1. 只要静态资源放在类路径下: /static, /public, /resources, /META-INF/resources 可以被直接访问-对应文件

WebProperties.java
```
java

public static class Resources {
        private static final String[] CLASSPATH_RESOURCE_LOCATIONS = 
new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
     classpath 表示类路径   
注意：classpath:/resources/ 表示服务器就会在 resources路径下找，你在浏览器当中输入的url地址的时候
，不可以输入 resources 目录，因为服务器就是会在 classpath:/resources/ 找的，而如果你写了resources在
浏览器上的话，你想表达的就是：让浏览器从resources/resouces的路径下找，这是找不到的报404错误
```

2. 常见静态资源: js,css,图片(.jpg,.png,.gif,.bmp,.svg) ,字体文件(Fonts)等
3. 访问方式: 默认：项目根路径/+静态资源名 比如: http://localhost:8080/hi.html ,设置WebMvcProperties.java
```
java
  this.staticPathPattern = "/**";

```


创建相关静态资源目录，并放入测试图片，没有目录，自己创建即可，完成测试
4. 注意：直接放在 resources 类的根路径下，是访问不到的。


# 静态资源访问注意事项和细节
1. 静态资源被访问原理：静态映射是 /** , 也就是对所有请求拦截，请求进来，先看Controller能不能处理，不能
处理的请求交给静态资源处理，如果静态资源找不到则相应 404页面

```
yaml
spring:
  mvc:
    static-path-pattern: /rainbowsea/** # 注意要加上 ** 才行
```


2. 改变静态资源访问前缀，比如我们希望 http://locahost:8080/rainbowsea/* 下的请求路径，去请求静态资源，
应用场景：静态资源访问前缀和控制器请求路径冲突
> 测试可以发现：是先找 controller 控制器，控制器不能处理该请求路径，再找的是静态资源的位置
>


3.改变默认的静态资源路径，比如：我们自己在类路径下增加 test目录，作为静态资源路径，并完成测试
```
yaml

spring:
  web:
    resources:
      # 修改/指定 静态资源的访问路径/位置
      static-locations: ["classpath:/test/"]# 仿写

```

注意：要保证 target 对应的目录下要有 5.jpg的文件。

4. 注意：上面你修改了 static-locations ，原来的访问路径就被覆盖了，如果
需要保留原来的，只需要把原来的默认的路径添加上就可以了。
```
yaml

```


