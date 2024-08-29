package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.bean.Furn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 表示配置类
public class FurnConfig {


    @Bean(value = "furn")
    public Furn getFurn() {
        return new Furn();
    }
}
