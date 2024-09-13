package com.rainbowsea.springboot.mybatispuls;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 *
 * @Mapper
 * public interface MonsterMapper extends BaseMapper<Monster> {
 *
 *  使用@MapperScan 可以指定要扫描的的Mapper接口
 *  属性basePackages 可以指定多个包，这里老师指定的是: com.rainbowsea.springboot.mybatispuls.mapper
 */
@MapperScan(basePackages = {"com.rainbowsea.springboot.mybatispuls.mapper"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);

    }

}
