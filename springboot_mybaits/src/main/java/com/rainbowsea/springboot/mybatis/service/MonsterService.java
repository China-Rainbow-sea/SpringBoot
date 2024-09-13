package com.rainbowsea.springboot.mybatis.service;

import com.rainbowsea.springboot.mybatis.bean.Monster;

public interface MonsterService {

    // 根据id 返回 Monster 对象
    public Monster getMonsterById(Integer id);
}
