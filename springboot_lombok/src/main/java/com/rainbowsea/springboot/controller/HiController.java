package com.rainbowsea.springboot.controller;


import com.rainbowsea.springboot.bean.Furn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController  // @Controller + @ResponseBody
public class HiController {


    @Autowired
    private Furn furn;


    @RequestMapping("/hi") // 设置请求映射路径
    public String hi(){
        log.info("furn-"+furn);

        // 占位符方式输出
        log.info("furn={} myfurn={}",furn,furn);


        return  "hi word";
        // 使用 slf4j日志输出
    }
}
