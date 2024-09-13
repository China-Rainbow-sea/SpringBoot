package com.rainbowsea.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor  // 空构造器
@AllArgsConstructor  // 全参数构造器  lombok
public class Furn {
    private Integer id;
    private String name;
    private String maker;
    private BigDecimal price;
    private  Integer sales;
    private  Integer stock;
    private String imgPath = "assets/images/product-image/1.jpg";
}