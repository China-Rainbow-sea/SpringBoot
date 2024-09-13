package com.rainbowsea.springboot.mybatis.controller;


import com.rainbowsea.springboot.mybatis.bean.Monster;
import com.rainbowsea.springboot.mybatis.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MonsterController {

    // 装配MonsterService
    @Resource
    private MonsterService monsterService;


    @ResponseBody
    @GetMapping("/monster")
    public Monster getMonsterById( @RequestParam(value = "id") Integer id) {
        return monsterService.getMonsterById(id);
    }
}
