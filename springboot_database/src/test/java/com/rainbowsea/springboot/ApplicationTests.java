package com.rainbowsea.springboot;


import com.rainbowsea.springboot.bean.Furn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest    // 该注解表示 spring boot 测试, 注意使用该 Spring boot 测试必须要定义编写对应的applicaton 场景类
public class ApplicationTests {

    // 大家可以回忆一下，在学习 spring 时，讲过的 JdbcTemplate
    @Resource  //Resource  注解默认根据名称装配byName，未指定name时，使用属性名作为 name，通过name 找不到的话会自动启动通过类型byType装配。
    private JdbcTemplate jdbcTemplate;


    @Test
    public void contextLoads() {
        BeanPropertyRowMapper<Furn> rowMapper = new BeanPropertyRowMapper<>(Furn.class);
        List<Furn> furns = jdbcTemplate.query("select * from furn", rowMapper);

        for (Furn furn : furns) {
            System.out.println(furn);
        }


        // 看看底层spring boot 使用的什么数据类型 [HikariDataSource]
        System.out.println(jdbcTemplate.getDataSource().getClass());
    }
}
