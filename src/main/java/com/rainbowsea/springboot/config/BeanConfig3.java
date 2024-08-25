package com.rainbowsea.springboot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration  // 标注配置类
// 导入 beans.xml 就可以获取到 beans.xml 中配置 bean
// 就是导入在 beans.xml 文件中配置的的 bean对象里面是 monster03 对象bena
// 注意：不要忘记了 Spring ，通过 xml 文件也是可以导入到 ioc 容器当中的
// locations 是数组类型，可以配置多个
// 流程是：在配置类当中——> 添加上@ImportResource(指明bean 的xml 路径，一般是类路径下，通过xml实现的bean 加入到ioc)
@ImportResource(locations = {"classpath:beans.xml","classpath:beans02.xml"})
public class BeanConfig3 {
}
