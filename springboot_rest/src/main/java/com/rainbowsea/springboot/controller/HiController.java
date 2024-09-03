package com.rainbowsea.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HiController {


    //@RequestMapping("/hello")
    @RequestMapping("/hello.html")  // 添加上了视图后缀的后，也同样可以访问该controller 文件了
    @ResponseBody
    public String hi(){
        System.out.println("sji");
        return "hi:):)";
    }


    @RequestMapping("/go")
    public String go(){
        System.out.println("spipt");
        return "hello";
        /*

return 是先看（controller对的）视图解析器当中是否有 hello.html 页面，没有就在找(视图解析器对的) controller 控制
是否有处理该请求的，如果两者都没有则报 404错误

添加上了：
spring:
  mvc:
    hiddenmethod:
      filter:
        enabled: true # 开启页面表单的rest功能，启用了HiddenHttpMethodFilter,支持rest
    #    static-path-pattern: /rainbowsea/**
#    view: # 配置视图解析器
#      prefix: / # 这里是需要注意，如果你配置了 static-path-pattern: /rainbowsea/** 需要保持一致
      #      prefix: /rainbowsea/ 都行 # 这里是需要注意，如果你配置了 static-path-pattern: /rainbowsea/** 需要保持一致
#      suffix: .html

视图解析器：就会走视图了，添加 hello.html 后缀了。 如果你的 @RequestMapping(没有html)后缀的映射
         的话是不会，走 controller ，因为视图解析器添加上了后缀，对应不上了@RequestMapping
         的映射路径了，还有：@RestController 注解：是不会跳转返回页面的都是，返回字符串/xml/json等等文件的。
         */



    }

}
