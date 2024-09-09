package com.rainbowsea.springboot.controller;


import com.rainbowsea.springboot.exception.AccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyErrorController {

    // 编写方法， 模拟一个AccessException
    @GetMapping("/err3")
    public String err3(String name) {

        // 如果用户不是tom，我们就认为：无权访问-模拟
        if (!"tom".equals(name)) {
            throw new AccessException("我们自己定义的异常");
            // 这里编写了放入的信息new AccessException("我们自己定义的异常") ，可以进行在后面放入
            // 到前端获取到，并显示出来。
            /*
            log.info("异常信息={}",e.getMessage());  // 自定义的异常也是可以获取到的
        // 这里老师将发生的异常放入到model,可以再错误页面取出显示
        model.addAttribute("msg",e.getMessage());
             */
        }

        return "manage"; // 视图地址
    }












    // 模拟一个服务器内部错误 500
    @GetMapping("/err")  // get 请求路径的映射
    public String err() {
        int i = 10 / 0;
        return "manage";
    }








    // 这里我们配置的是Post方式请求 / err2
    // 一会使用 get方式来请求 /err2，这样就会出现一个4开头的客户端请求
    @PostMapping("/err2")
    public String err2() {
        return "manage";
    }









}
