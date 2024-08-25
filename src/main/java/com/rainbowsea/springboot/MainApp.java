package com.rainbowsea.springboot;


import com.rainbowsea.springboot.bean.A;
import com.rainbowsea.springboot.bean.Cat;
import com.rainbowsea.springboot.bean.Dog;
import com.rainbowsea.springboot.bean.Monster;
import com.rainbowsea.springboot.config.BeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;


// @SpringBootApplication 表示这是一个springBoot 应用/项目
// @SpringBootApplication(scanBasePackages = {"com.rainbowsea"}) 表示 scanBasePackages = {"com.rainbowsea"} 指定
// springBoot 要扫描的包路径
// 如果有多个包：因为scanBasePackages属性类型是一个数组所以，可以 scanBasePackages = {"com.rainbowsea","xxx.xxx.x"}
@SpringBootApplication(scanBasePackages = {"com.rainbowsea"})
public class MainApp {
    public static void main(String[] args) {
        // 启动springboot应用程序/项目
        //SpringApplication.run(MainApp.class,args);


        // 启动SpringBoot 应用程序/项目
        // 这里使用接口来对接上
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApp.class, args);

        //
        // 如何查看容器中注入的组件
        /*        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName --" + beanDefinitionName);
        }*/


        // 演示Spring中传统的注解依然可以使用 @Controller,@Service, @Repository,@Component  start
        /*        A aBean = ioc.getBean(A.class);
        System.out.println("aBean --" + aBean);
        */

        // 演示Spring中传统的注解依然可以使用 @Controller,@Service, @Repository,@Component  end
//


        // 演示在Spring Boot 项目中，依然可以使用Spring的配置bean/注入bean/获取bean方式 start
/*
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        //ac.getBean("xml中配置的id",class)
        Monster monster03 = ac.getBean("monster03", Monster.class);
        System.out.println("monster03--" + monster03);
*/

        // 演示在Spring Boot 项目，依然可以使用Spring的配置bean/注入bean/获取bean方式  end











        // 演示 Spring Boot 项目中：     @Bean(name = {"monster_nmw"}) 自定义别名的方式 start


/*        Monster monster_nmw = ioc.getBean("monster_nmw", Monster.class);
        System.out.println("monster_nmw --- " + monster_nmw);*/


        // 演示 Spring Boot 项目中：     @Bean(name = {"monster_nmw"}) 自定义别名的方式 end






        // 演示 @Configuration start
/*        Monster monster01 = ioc.getBean("monster01", Monster.class);
        Monster monster02 = ioc.getBean("monster01", Monster.class);
        System.out.println("monster01 ---" + monster01 + " " + monster01.hashCode());
        System.out.println("monster02 ---" + monster02 + " " + monster02.hashCode());*/

        // 特别说明：默认Spring Boot 是单例获取的 Bean对象的，叶就是获取到的Bean是同一个对象
        // 在对应的 Config配置类中定义：  @Scope(value = "prototype") 就是多例的了。
        // 演示 @Configuration end





        //=== 演示 被@Configuration 修饰标识的配置类-bean,也会被注入到ioc容器当中  start
/*        BeanConfig bean = ioc.getBean(BeanConfig.class);
        System.out.println("bean-- " + bean);*/
        // ===演示 被@Configuration 修饰标识的配置类-bean,也会被注入到ioc容器当中  end




        // 演示@Configuration(ProxyBeanMethods = xxx) 单例/ 多例的设置  start

        // 先得到 BeanConfig 组件，再从 BeanConfig 组件中调用方法获得其它的Bean
        /*BeanConfig beanConfig = ioc.getBean(BeanConfig.class);

        Monster monster_01 = beanConfig.monster01();
        Monster monster_02 = beanConfig.monster01();

        System.out.println("monster_01 " + monster_01 + " " + monster_01.hashCode());
        System.out.println("monster_02 " + monster_02 + " " + monster_02.hashCode());
*/



        // 特别说明: proxyBeanMethods 是在调用@Bean方法才生效，因此，需要先获取BeanConfig 组件，再调用其中BeanConfig配置类组件中的方法获取
        // 1. 而不是直接通过 SpringBoot 主程序得到的容器来获取Bean(ioc.getBean())，注意观察直接通过ioc.getBean()获取是@Configuration
        // (ProxyBeanMethods = xxx) 属性配置是失效的（还是默认的单例）
/*
        Monster monster01 = ioc.getBean("monster01", Monster.class);
        Monster monster02 = ioc.getBean("monster01", Monster.class);

        System.out.println("monster01 " + monster01 + " " + monster01.hashCode());
        System.out.println("monster02 " + monster02 + " " + monster02.hashCode());

*/


        // 演示@Configuration(ProxyBeanMethods = xxx) 单例/ 多例的设置  end






        // 测试可以有多个配置类 start,
        // 没有在 @Bean注解当中指明的话，默认是采用该“方法名”作为id/名字
/*        Monster monster02 = ioc.getBean("monster02", Monster.class);
        System.out.println("monster02 " + monster02);*/
        // 测试可以有多个配置类 end




        //  测试 @Import 使用 start
/*        Dog dogBean = ioc.getBean(Dog.class);
        Cat catBean = ioc.getBean(Cat.class);
        System.out.println("doBean -- " + dogBean);
        System.out.println("catBean--" + catBean);*/
        //  测试 @Import 使用 end


        // 演示: @ConditionalOnBean 条件注入 使用 start
    /*    Dog dogBean = ioc.getBean(Dog.class);
        System.out.println("doBean -- " + dogBean);*/
        // 演示: @ConditionalOnBean 条件注入 使用 end









        // 演示 @ImportResource 使用 start
/*
        Monster monster03 = ioc.getBean("monster03", Monster.class);
        System.out.println("monster03-" + monster03);
        System.out.println("monster03 bean 是否存在到 ioc 容器中" + ioc.containsBean("monster03"));

        System.out.println("____________________________");
        Monster monster04 = ioc.getBean("monster04", Monster.class);
        System.out.println("monster04-" + monster04);
        System.out.println("monster04 bean 是否存在到 ioc 容器中" + ioc.containsBean("monster04"));
*/


        // 演示 @ImportResource 使用 end

    }
}


/*
 * 1. SpringBoot 内置了Tomcat，简化服务器的设置，
 * 2. 当然 SpringBoot 还有很多优势，后面继续说明
 *
 * */