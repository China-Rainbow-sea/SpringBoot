package com.rainbowsea.springboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 关于自定义异常，我们在Java基础中是讲解过 => 融合贯通
 *
 * @ResponseStatus(value = HttpStatus.FORBIDDEN) : 表示发生 AccessException异常，我们通过http
 * 协议返回的状态码 403
 * 这个状态码和自定义异常的对应关系是由程序员来决定【尽量合理来设置】
 */

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessException extends  RuntimeException{

    // 老师提供一个构造器，可以指定信息
    public AccessException(String message) {
        super(message);
        System.out.println("AccessException 有参数构造方法被调用");
    }

    // 显示的定义一个无参构造器
    public AccessException() {
        System.out.println("AccessException 无参数构造方法被调用");

    }
}
