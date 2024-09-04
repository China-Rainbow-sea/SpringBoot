package com.rainbowsea.springboot.bean;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 无参数
public class Admin {
    private String name;
    private String password;

}
