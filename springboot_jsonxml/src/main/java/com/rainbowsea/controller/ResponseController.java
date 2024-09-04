package com.rainbowsea.controller;


import com.rainbowsea.bean.Car;
import com.rainbowsea.bean.Monster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseController {


    // 返回 Monster 数据-要求以JSON格式返回
    @GetMapping("/get/monster")
    @ResponseBody
    public Monster getMonster() {

        // monster 对象是从DB数据库获取-这里老师模拟一个monster对象
        Monster monster = new Monster();
        monster.setId(100);
        monster.setName("奔波霸");
        monster.setAge(200);
        monster.setIsMarried(false);
        monster.setBirth(new Date());
        Car car = new Car();
        car.setName("奔驰");
        car.setPrice(222.2);
        monster.setCar(car);

        return monster;
    }

}
