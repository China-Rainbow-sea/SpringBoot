package com.rainbowsea.springboot.mybatis;


import com.rainbowsea.springboot.mybatis.bean.Monster;
import com.rainbowsea.springboot.mybatis.mapper.MonsterMapper;
import com.rainbowsea.springboot.mybatis.service.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)  // 与 main 不同的需要指明 测试的是哪个Class类
public class ApplicationTest {


    @Resource
    private JdbcTemplate jdbcTemplate;


    @Resource
    private MonsterMapper monsterMapper;

    // 装配到MonsterService
    @Resource
    private MonsterService monsterService;


    @Test
    public void t1() {
        // 输出看看当前的数据源是什么
        System.out.println(jdbcTemplate.getDataSource().getClass());

    }


    @Test
    public void getMonsterById() {
        Monster monsterById = monsterMapper.getMonsterById(1);
        System.out.println(monsterById);

    }



    // 测试 MonsterService
    @Test
    public void getMonsterById2() {

        Monster monster = monsterService.getMonsterById(2);
        System.out.println(monster);

    }



}
