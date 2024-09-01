# Rest 风格请求处理



# Rest风格请求-注意事项和细节
1.客户端是PostMan 可以直接发送Put，delete等方式请求，可不设置Filter
2. 如果哟啊SpringBoot支持页面表达的 Rest 功能，则需要注意如下细节：

1》Rest 风格请求核心 Filter: HiddenHttpMethodFilter，表单请求会被 HiddenHttpMethodFilter拦截
，获取到表单_method的值，再判断PUT/DELETE/PATCH(patch方法是新引入的，是对Put方法的补充，用来对已知资源进行局部更新:
) https://segmentfault.com/q/1010000005685904

2》如果要SpringBoot 支持页面表单的Rest功能，需要在application.yml 启用 filter功能，否则无效
3》修改application.yml (resources 类路径下) 启用filter功能。

```
yaml
spring:
  mvc:
    hiddenmethod:  # 有两个注意不要弄错了。
      filter:
        enabled: true # 开启页面表单的rest功能
```

> Rest的核心过滤器
> 1. 当前的浏览器只支持 post/get请求，因此为了得到 put/delete的请求方式需要提供的 HiddenHttpMethodFilter过滤器进行转换
> 2. HiddenHttpMethodFilter : 浏览器 form 表单只支持 get 和 post 请求，而delete,put 等method并不支持，
>spring添加了一个过滤器，可以将这些请求转换为标准的 http 方使得支持get,post,put和delete请求
>3. HiddenHttpMethodFilter 能对 post 请求方式进行转换，因此我们需要特别的注意这一点
>4. 这个过滤器需要在 web.xml 中配置
>

```
yaml

```

思考题:
1. 留一个思考题，为什么这里 return "GET-查询妖怪"，返回的是字符串，而不是转发到对应的资源文件。
```
java

因为@RestController 是一个复合注解，含有@Controller + @ResponseBody ，所以springboot底层
return "xxx" 时，会以 @ResponseBody注解进行解析处理，即返回字符串"xxx",而不会使用视图解析
同学们，可以试一下，如果我们把@RestController 改成 @Controller，当你访问 getMonster()时，
如果你有xxx.html，就会转发到xxx.html ，如果没有xxx.html ，就会报404
// 提示，将 xxx.html 放到 resources\public\xxx.html 静态路径下测试

@RestController
public class HiController {


    @RequestMapping("/hello")
    public String hi(){
        return "hi:):)";
    }

```


```
yaml

spring:
  mvc:
    view: # 配置视图解析器
      prefix: / # 这里是需要注意，如果你配置了 static-path-pattern: /rainbowsea/** 需要保持一致
      suffix: .html

#    static-path-pattern: /rainbowsea/**


```


```
yaml
spring:
  mvc:
    hiddenmethod:
      filter:
        enabled: true # 开启页面表单的rest功能，启用了HiddenHttpMethodFilter,支持rest
    view: # 配置视图解析器
      prefix: /rainbowsea/** # 这里是需要注意，如果你配置了 static-path-pattern: /rainbowsea/** 需要保持一致
#      prefix: /rainbowsea/ 都行 # 这里是需要注意，如果你配置了 static-path-pattern: /rainbowsea/** 需要保持一致
      suffix: .html

    static-path-pattern: /rainbowsea/**

```