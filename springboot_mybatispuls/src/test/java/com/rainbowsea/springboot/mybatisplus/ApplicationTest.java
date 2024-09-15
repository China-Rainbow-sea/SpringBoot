package com.rainbowsea.springboot.mybatisplus;


import com.rainbowsea.springboot.mybatispuls.Application;
import com.rainbowsea.springboot.mybatispuls.bean.Monster;
import com.rainbowsea.springboot.mybatispuls.mapper.MonsterMapper;
import com.rainbowsea.springboot.mybatispuls.service.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {



    @Resource
    private MonsterMapper monsterMapper;

    @Test
    public void testMonsterMapper() {
        Monster monster = monsterMapper.selectById(2);
        System.out.println("monster--" + monster);
    }





    @Resource
    private MonsterService monsterService;


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








    @Resource
    private JdbcTemplate jdbcTemplate;



    @Test
    public void getDruidSourceTest() {
        System.out.println(jdbcTemplate.getDataSource().getClass());
    }







}
