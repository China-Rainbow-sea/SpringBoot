package com.rainbowsea.springbootyaml.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


@ConfigurationProperties(prefix = "monster")
@Component
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Monster {
    private Integer id;
    private String name;
    private Boolean isMarried;
    private Integer age;
    private Date birth;
    private Car car;
    private String[] skill;
    private List<String> hobby;
    private Map<String,Object> wife;
    private Set<Double> salaries;
    private Map<String,List<Car>> cars;

}
