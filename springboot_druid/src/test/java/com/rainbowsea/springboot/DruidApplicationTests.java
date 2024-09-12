package com.rainbowsea.springboot;


import com.rainbowsea.springboot.bean.Furn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest // spring boot 测试
public class DruidApplicationTests {


    @Resource // spring 当中的非简单类型的自动赋值
    private JdbcTemplate jdbcTemplate;


    @Test
    public void contextLoads() {
        BeanPropertyRowMapper<Furn> rowMapper = new BeanPropertyRowMapper<>(Furn.class);
        List<Furn> furns = jdbcTemplate.query("select * from furn", rowMapper);
        for (Furn furn : furns) {
            System.out.println(furn);
        }


        // 看看底层使用的是什么数据库连接池
        System.out.println(jdbcTemplate.getDataSource().getClass());
    }



}
