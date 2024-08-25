package com.rainbowsea.springboot.bean;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;




//@Component // 注入到 ioc 容器当中,第二种配置绑定方式要注销掉 @Component 不需要他
@ConfigurationProperties(prefix = "furn01")  // prefix = "前缀"

public class Furn {
    private Integer id;
    private String name;
    private Double price;


    public Furn() {
    }

    public Furn(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Furn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
