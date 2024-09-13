package com.rainbowsea.springboot.mybatispuls.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbowsea.springboot.mybatispuls.bean.Monster;


/**
 * 1.传统方式：在接口中定义方法/声明方法，然后在实现类中进行实现
 * 2. 在mybatis-plus中，我们可以继承父接口 IService
 * 3. 这个 IService 接口声明很多方法，比如crud
 * 4. 如果默认提供方法不能满足需要，我们可以再声明需要的方法，然后在实现类中进行实现即可
 */

// 注意：接口不要添加 @Service
public interface MonsterService extends IService<Monster> {
    // 自定义方法
    public void t1();
}
