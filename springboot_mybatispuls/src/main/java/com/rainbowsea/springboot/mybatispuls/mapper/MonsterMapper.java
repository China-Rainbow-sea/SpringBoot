package com.rainbowsea.springboot.mybatispuls.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbowsea.springboot.mybatispuls.bean.Monster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * BaseMapper 已经默认提供了很多的crud 方法，可以直接使用
 * 如果BaseMapper 提供的方法不能满足业务需求，我们可以再开发新的方法，
 * 并在MonsterMapper.xml 进行配置 ===> 使用插件开发
 */


@Mapper
public interface MonsterMapper extends BaseMapper<Monster> {

    // 自定义方法
    // 使用 mybatisx 插件

    int insertSelective(Monster monster);

    int delByEmail(@Param("email") String email);



}
