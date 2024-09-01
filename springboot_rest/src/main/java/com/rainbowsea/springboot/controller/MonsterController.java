package com.rainbowsea.springboot.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController  // @Controller + @ResponseBody
public class MonsterController {


    // 等价写法
    //@RequestMapping(value = "/monster",method = RequestMethod.GET)
    @GetMapping("/monster")
    public String getMonster() {
        return "GET-查询妖怪";
    }


    // 等价写法
    //@RequestMapping(value = "/monster",method = RequestMethod.POST)
    @PostMapping("/monster")
    public String saveMonster() {
        return "POST-添加妖怪";
    }


    // 等价写法
    //@RequestMapping(value = "/monster",method = RequestMethod.PUT)
    @PutMapping("/monster")
    public String putMonster() {
        return "PUT-修改妖怪";
    }

    // 等价写法
    //@RequestMapping(value = "/monster", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/monster")
    public String delMonster() {
        return "DELETE-修改妖怪";
    }

}
