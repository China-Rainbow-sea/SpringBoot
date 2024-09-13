package com.rainbowsea.springboot.mybatispuls.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rainbowsea.springboot.mybatispuls.bean.Monster;
import com.rainbowsea.springboot.mybatispuls.mapper.MonsterMapper;
import com.rainbowsea.springboot.mybatispuls.service.MonsterService;
import org.springframework.stereotype.Service;


/**
 * 1. 传统方式：在实现类中直接进行implements MonsterService
 * 2. 在mybatis-puls 中，我们开发Service 实现类，需要继承 ServiceImpl
 * 3. 我们观察看到 ServiceImpl 类实现 IService 接口
 * 4. MonsterService 接口它继承了 IService 接口
 * 5. 这里MonsterServiceImpl 就可以认为是实现了 MonsterService接口，这样MonsterService
 * 就可以使用IService接口方法，也可以理解可以使用 MonsterService 方法
 * 6. 如果 MonsterService接口中，声明了其它的方法/自定义方法，那么我们依然需要在MonsterService
 * 类，进行实现
 */

@Service
public class MonsterServiceImpl
    extends ServiceImpl<MonsterMapper, Monster>
        implements MonsterService {


    @Override
    public void t1() {

    }
}
