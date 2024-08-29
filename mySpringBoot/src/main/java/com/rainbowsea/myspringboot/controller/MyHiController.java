package com.rainbowsea.myspringboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   // @Controller +  @ResponseBod
public class MyHiController {

    @RequestMapping("/myhi")  // 设置请求映射路径
    public String hi() {
        return "hi my MyHiController ";
    }

}
