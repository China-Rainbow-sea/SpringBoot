package com.rainbowsea.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
public class HiController {

    @RequestMapping("1.jpg")  // 默认请求的路径和资源的名字冲突
    public String hi(){
        return "hi";
    }
}
