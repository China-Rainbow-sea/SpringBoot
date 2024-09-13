package com.rainbowsea.springboot.mybatisplus;


import com.rainbowsea.springboot.mybatis.bean.Monster;
import com.rainbowsea.springboot.mybatispuls.Application;
import com.rainbowsea.springboot.mybatispuls.mapper.MonsterMapper;
import com.rainbowsea.springboot.mybatispuls.service.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Resource
    private MonsterMapper monsterMapper;


    @Resource
    private MonsterService monsterService;

    @Test
    public void testMonsterMapper() {
        Monster monster = monsterMapper.selectById(2);
        System.out.println("monster--" + monster);
    }


    @Test
    public void testMonsterService() {
        Monster monster = monsterService.getById(2);
        System.out.println(monster);

        // 这些 List 都是 monsterService 继承 IService 当中的方法
        List<Monster> list = monsterService.list();

        for (Monster monster1 : list) {
            System.out.println(monster1);
        }
    }

}
