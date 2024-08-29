package com.rainbowsea.springbootyaml.controller;


import com.rainbowsea.springbootyaml.bean.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController  // @Controller + @ResponseBody
public class HiController {


    //@Resource  // 通过 yaml 注入赋值 @Resource 和 Autowired 都可以
    @Autowired
    private Monster monster;


    @RequestMapping("/monster")  // 设置请求的映射路径
    public Monster monster(){
        return monster;
    }
}
