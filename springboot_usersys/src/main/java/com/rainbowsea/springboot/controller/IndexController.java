package com.rainbowsea.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 编写方法，转发到登录页面
    /*
    解释:
    1.因为我们引入了 starter-thymeleaf
    2.这里就会直接使用视图解析到thymeleaf下的模板文件admin
     */
    @GetMapping(value = {"/","/login"})
    public String login(){
        return "adminLogin";
    }
}
