package com.rainbowsea;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HiController {

    // 返回hi,springboot
    @ResponseBody
    @RequestMapping(value = {"/hi"})
    public String hi(){
        System.out.println("website" + website);
        return "hi~ Spring Boot";
    }


    // 需求website，属性值从application.properties 的 k-v
    @Value("${my.website}")
    private String website;
}
