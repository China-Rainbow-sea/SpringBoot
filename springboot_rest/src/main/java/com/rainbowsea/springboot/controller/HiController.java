package com.rainbowsea.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {


    @RequestMapping("/hello")
    public String hi(){
        return "hi:):)";
    }


    @RequestMapping("/go")
    public String go(){
        return "hello";
        /*

return 是先看视图解析器当中是否有 hello.html 页面，没有就在找 controller 控制
是否有处理该请求的，如果两者都没有则报 404错误
         */
    }

}
