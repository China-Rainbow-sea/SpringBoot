package com.rainbowsea.springboot.mybatis.service.impl;


import com.rainbowsea.springboot.mybatis.bean.Monster;
import com.rainbowsea.springboot.mybatis.mapper.MonsterMapper;
import com.rainbowsea.springboot.mybatis.service.MonsterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MonsterServiceImpl implements MonsterService {


    // 装配MonsterMapper

    @Resource
    private MonsterMapper monsterMapper;


    @Override
    public Monster getMonsterById(Integer id) {


        return monsterMapper.getMonsterById(id);
    }
}
