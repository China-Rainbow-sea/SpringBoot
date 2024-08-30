package com.rainbowsea.springboot.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@ToString  // 在编译时，生成 toString，注意：默认情况下，会生成一个无参构造器
//@Data // 注解等价使用了，如下注解: @Getter,@Setter,@RequiredArgsConstructor @ToString,@EqualsAndHas
// #RequiredArgsConstructor :单独说明
//

//@NoArgsConstructor  // 在编译时，会生成无参构造器（一定会生成）
// 特别说明，虽然上面的@Data,@ToString注解等等，默认情况下都会生成一个无参构造器，但是当你使用了多个注解的时候，可能会覆盖掉无参构造器
// 但是当我们有其它多个构造器生成时，你如果你希望仍然有无参构造器就需要使用 @NoArgsConstructor 注解了，因为 @NoArgsConstructor
// 是一定会生成一个无参构造器的（无参构造器很重要，因为框架的使用是涉及到反射机制的，而反射机制，需要一个无参构造器，否则你
// 就无法进行反射获取 bean对象，框架也就无法使用了），
/*
@AllArgsConstructor // 在编译时，会生成全参数构造器
@Setter
@Getter*/

//@Data// 注解等价使用了，如下注解: @Getter,@Setter,@RequiredArgsConstructor @ToString,@EqualsAndHas
//@NoArgsConstructor //在编译时，会生成无参构造器（一定会生成），不会受到其它的








@AllArgsConstructor
@NoArgsConstructor
@Data
public class Furn {

    private Integer id = 100;

    private String name = "张三";
    private Double price = 999.0;


}
