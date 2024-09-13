package com.rainbowsea.springboot.mybatis.mapper;

import com.rainbowsea.springboot.mybatis.bean.Monster;
import org.apache.ibatis.annotations.Mapper;


/**
 * 在Mapper接口使用 @Mapper 就会扫描，并将Mapper接口对象注入
 */
@Mapper // 包扫描，加上了这个注解的话，那么
public interface MonsterMapper {


    // 方法 根据id 返回 Monster 对象
    public Monster getMonsterById(Integer id);

}
