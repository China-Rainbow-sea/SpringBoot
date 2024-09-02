package com.rainbowsea.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
public class HiController {

    @RequestMapping("1.jpg")  // Controller 控制处理的请求的路径和静态资源的名字冲突
    public String hi(){
        return "hi";
    }
}
