package com.rainbowsea.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
// @Controller + @ResponseBody
public class testController {


    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
