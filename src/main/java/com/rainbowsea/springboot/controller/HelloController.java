package com.rainbowsea.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {



    // 返回 hello Springboot
    @RequestMapping(value = {"/hello"})  // 请求路径设置
    @ResponseBody  // 表示返回的不是一个页面，而是 json或者是字符串
    public String hello(){
        return "hello SpringBoot";
    }

}
