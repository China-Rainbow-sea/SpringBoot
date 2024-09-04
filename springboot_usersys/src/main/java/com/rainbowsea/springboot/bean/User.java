package com.rainbowsea.springboot.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 无参数
@AllArgsConstructor  // 全参数
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private String email;

}
