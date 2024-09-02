package com.rainbowsea.springboot.config;


import com.rainbowsea.springboot.bean.Car;
import com.rainbowsea.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 标志配置类
public class WebConfig {
    @Bean // 注如到 ioc容器当中
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                /**
                 * 老师解读
                 * 1. 在addFormatters方法中，增加一个自定义的转换器
                 * 2. 增加自定义转换器 String->car
                 * 3. 增加的自定义转换器会注册到converters容器中
                 * 4. converters 底层结构时 ConcurrentHashMap 内置了124个转换器
                 * 5. 一会老师会使用 debug 来看到这些转换器
                 */
                Converter<String,Car> converter = new Converter<String, Car>() { // 第一个参数是要转换的类型，第二个参数是想要转换成什么类型
                    @Override
                    public Car convert(String source) {  // source 就是传入的字符串，避水金晶兽
                        // 这里就加入你的自定义的转换业务处理
                        //if(StringUtils)
                        if(!ObjectUtils.isEmpty(source)) {
                            Car car = new Car();
                            String[] split = source.split(",");
                            car.setName(split[0]);
                            car.setPrice(Double.parseDouble(split[1]));
                            return car;
                        }
                        return null;
                    }
                };


                // 添加转换器converter3 重复了
                Converter<String,Car> converter3 = new Converter<String, Car>() { // 第一个参数是要转换的类型，第二个参数是想要转换成什么类型
                    @Override
                    public Car convert(String source) {  // source 就是传入的字符串，避水金晶兽
                        // 这里就加入你的自定义的转换业务处理
                        //if(StringUtils)
                        if(!ObjectUtils.isEmpty(source)) {
                            System.out.println("source-" + source);
                        }
                        return null;
                    }
                };

                // 第2个自定义转换器
                // 还可以增加更多的转换器
                Converter<String,Monster> converter2 = new Converter<String, Monster>() { //
                    // 第一个参数是要转换的类型，第二个参数是想要转换成什么类型
                    @Override
                    public Monster convert(String source) {  // source 就是传入的字符串，避水金晶兽
                        // 这里就加入你的自定义的转换业务处理
                        //if(StringUtils)
                        if(!ObjectUtils.isEmpty(source)) {
                            Monster monster = new Monster();
                            String[] split = source.split(",");
                            return monster;
                        }
                        return null;
                    }
                };

                // 添加自定义的转换器
                registry.addConverter(converter);
                registry.addConverter(converter2);
                registry.addConverter(converter3);
            }
        };



    }
}
