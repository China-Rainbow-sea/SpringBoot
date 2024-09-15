package com.rainbowsea.springboot.mybatispuls.controller;



import com.rainbowsea.springboot.mybatispuls.bean.Monster;
import com.rainbowsea.springboot.mybatispuls.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MonsterController {

    @Resource
    private MonsterService monsterService;

    // 方法，根据id 返回对应对象
    @ResponseBody
    @GetMapping("/monster")
    public Monster getMonsterById(@RequestParam(value = "id") Integer id) {
        return monsterService.getById(id);
    }

    // 编写方法，返回所有的monster信息
    // 后面我们还会说分页查询
    @GetMapping("/list")
    @ResponseBody
    public List<Monster> listMonster() {
        return monsterService.list();
    }


}
