package com.rainbowsea;


import com.rainbowsea.springboot.bean.Furn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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


    //将 Furn  装配到 HiController 类当中
    // 装配完了，则furn 类的属性值，就是你在 application.properties当中配置的值
    @Resource
    private Furn furn;




    @RequestMapping("/furn")  // 映射路径
    @ResponseBody  // 表示返回的是一个json/字符串，不是一个页面
    public Furn furn(){
        return furn;
    }
}
