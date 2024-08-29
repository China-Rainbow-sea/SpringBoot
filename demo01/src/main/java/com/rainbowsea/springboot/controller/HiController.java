package com.rainbowsea.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 是 @Controller + @ResponseBody 两个注解的组合形式
public class HiController {



    @RequestMapping("/hi")  // 请求映射路径
    public String hi(){
       return  "hi ranibowsea HiController";
    }
}
