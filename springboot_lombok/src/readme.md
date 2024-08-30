# Lombok介绍

* Lombok 作用
> 1. 简化JavaBean开发，可以使用Lombok的注解让代码更加简洁
> 2. Java项目中，很多没有技术含量但又必须存在的代码：比如：Pojo 的 getter/setter/toString ；异常处理：I/O流的关闭操作等等
> 这些代码既没有技术含量，又影响着代码的美观，Lombok应运而生
>
>



* Spring Boot 和 IDEA 官方支持
> 1. IDEA 2020已经内置了 Lombok插件
> 2. Spring Boot 2.1.x之后的版本也在 Stater中内置了 Lombok依赖
>
>

```
java

@Data：注解在类上，提供类所有属性的getting和setting方法，此外还提供了equals,canEqual,hashCode,toString方法
@Setter: 注解在属性上，为属性提供 setting 方法
@Getter: 注解在属性上，为属性提供 getting 方法
@Log4j：注解在类上：为类提供了一个属性名为: log 的 log4j的日志对象
@NoArgsConstructor: 注解在类上，为类提供一个无参的构造方法,(一定会提供)
@AllArgsConstructor: 注解在类上，为类提供一个全参的构造方法
@Cleanup:可以关闭流
@Builder: 被注解的类加个构造者模式
@Synchronized: 加同步锁
@SneakyThrows: 等同于try/catcher捕获异常
@NonNull:如果给参数加个这个注解，参数为null会抛出空指针异常
@Value: 注解和@Value类似，区别在于它会把所有成员变量默认定义为 private final 修饰，并且不会生产set()方法

```


使用lombok注解简化代码，可以通过idea自带的反编译功能 target，看Furn.class的源码，就
可以看到生成的完整代码。


特别说明: @Data 中的 @RequiredArgsConstructor
在我们写controller 或是 Service层的时候，需要注入很多的 mapper接口或者另外的service接口，这时候就会写很多
的@Autowired注解，代码看起来很乱。Lombok 提供了一个注解:
@RequiredArgsConstructor(onConstructor=@_(@AutoWired))
写在类上可以代替@Autowired注解，需要注意的时在注入时，需要用final定义，或者使用 @notnull注解 


在idea 安装 lombok插件

不装插件也可以用基本的注解比如：@Data,@Getter...
但是不能使用其扩展功能，比如日志输出...，所以我们还是安装一下，也比较简单。
直接去插件商城，搜索: Lombok 即可