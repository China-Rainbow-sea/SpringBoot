package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.bean.Cat;
import com.rainbowsea.springboot.bean.Dog;
import com.rainbowsea.springboot.bean.Furn;
import com.rainbowsea.springboot.bean.Monster;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;


/*
1. @Configuration  标识这是一个配置类: 等价  配置文件
2. 程序员可以通过 @Bean 注解注入bean对象到容器中
3. 当一个类被 @Configuration 标识，则该类-Bean ，也会注入到容器当中，以配置类的形式存在于其中
*/

/*
@Configuration 注意事项和细节： 自定义配置类的
> 1. proxyBeanMethods : 代理bean的方法
> 2. Full(proxyBeanMethods = true) 单例：保证每个@Bean方法被调用多少次返回的组件都是单实例的，是代理方式，是共用的
> 3. Lite(proxyBeanMethods = false) 多例： 每个@Bean方法被调用多少次，返回的组件都是新创建的，是非代理方式 ，非共用的
>特别说明：proxyBeanMethods 是在调用 @Bean方法才生效，因此，需要先获取到对应 BeanConfig(配置类的组件)，再从配置类组件的方法
>中，调用方法，获取到的Bean，而不是直接通过 SpringBoot 主程序的 ioc,getBean()直接得到容器来获取 bean,
>注意观察直接通过ioc.getBean()获取 Bean, proxyBeanMethods 值并没有生效。
> 4 . 如何选择：组件依赖必须使用Full模式(默认就是 full模式)，如果不需要组件依赖使用 Lite模式
> 5. Lite 模式也称之为：“轻量级模式”，因为不检测依赖关系，运行速度快。
 */
//@Configuration(proxyBeanMethods = true)  // 单例



/*
@Documented
public @interface Import {
    Class<?>[] value();
}
 @Import 代码，可以看到，可以指定 class 的数组，可以注入指定类型的Bean 到 ioc 容器当中
 2. 通过 @Import 方式注入了组件，默认组件名字/id就是对饮类型的全类名
 */

/***
 * @EnableConfigurationProperties({Furn.class})
 * 1. 开启Furn配置绑定功能
 * 2. 把 Furn 组件自动注册到容器中
 * 3. 这种方式用的比较少，了解即可
 * 4. 注意是：在标有@Configuration 配置类上，声明
 */

//@Import({Dog.class, Cat.class})
@Configuration(proxyBeanMethods = false)  // 多例
@EnableConfigurationProperties({Furn.class})
public class BeanConfig {

    /*
    * 解读:
    * 1. @Bean ： 给容器添加组件，就是 Monster bean
    * 2. monster01() ： 默认：你的方法名monster01，作为 Bean 的名字/id
    * 3. Monster: 注入类型，注入的bean的类型是Monster
    * 4. new Monster(2000,"牛魔王",5000,"芭蕉扇") 注入到容器中具体的Bean 信息
    * 5.     @Bean(name = {"monster_nmw"}) 在配置，注入Bean指定名字/id ，name是数组类型，可以定义多个
    * 6. 默认是单例注入的：（也就是说 getBean 获取到的都是同一个对象，共用）
    * 7. 通过    @Scope(value = "prototype") 可以每次返回一个新的对象，多例
    * */


    //@Bean(name = {"monster_nmw"})
    //@Bean
    //@Scope(value = "prototype")
    public Monster monster01(){
        return new Monster(2000,"牛魔王",5000,"芭蕉扇");
    }


    /**
     * 1.@CondittionalOnBean(name="monster_nmw") 表示
     * 2.当容器中有一个Bean，名字是monster_nmw(类型不做约束，仅仅是 id /名字做约束)，就注入dog01这个Dog bean
     * 3.如果ioc容器中，并没有一个叫 "monster_nmw" 的对象，该 Dog就不会注入 dog01的这个 Dog bean 对象到 ioc 容器当中
     * 4. 还有很多其它的条件约束注解，小伙伴可以自己测试
     * 5.注意：使用 @ConditionalOnBean 的同时，要将 @import 注解去了，因为 @import可以实现 ioc注入
     * 6. 注意：满足条件的，要放到判断的前面，简单的说就是，作为条件的bean要放到判断的bean的前面，不然是无法判断的
     * 7.     @ConditionalOnMissingBean(name = {"monster_nmw"})  没有名字/id 为 monster_nmw 才注入 dog01这个 Bean
     * 8. @ConditionalOnBean(name = {"monster_nmw"}) 也可以放在配置类，类的上面
     * 表示对该配置类的所有要注入的组件，都进行条件约束。
     * @return
     */

    //@Bean(name = {"monster_nmw"})
    @Bean
    public Cat cat01(){
        return new Cat();
    }

    @Bean  // @Bean注解当中没有指明名称，则默认方法名就是 id/名称
    //@ConditionalOnBean(name = {"monster_nmw"})  // name 属性是个数组，可以写多个条件
    @ConditionalOnMissingBean(name = {"monster_nmw"})  // 没有非的判断name 属性是个数组，可以写多个条件
    public Dog dog01(){
        return new Dog();
    }





}
