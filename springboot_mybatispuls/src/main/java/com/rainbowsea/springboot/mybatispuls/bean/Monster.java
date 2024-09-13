package com.rainbowsea.springboot.mybatispuls.bean;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

/*
如果这个类名Monster 和 表名 monster 一致，可以映射上，则 @TableName 可以省略
如果这个类名Monster 和 表名 不一致，不能映射上，则可以通过 @TableName 指定,让表名和数据表一致
 */

@TableName("monster_") // 填数据库表当中定义的表名
public class Monster {

    private Integer id;
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;
    private String email;
    private String name;
    private String gender;
    private Double salary;


}
