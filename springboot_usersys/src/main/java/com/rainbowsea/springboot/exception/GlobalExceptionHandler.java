package com.rainbowsea.springboot.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice  // 使用它可以标识一个全局异常处理器/对象
public class GlobalExceptionHandler {
    // 1.编写方法，处理指定异常，比如：我们处理算术异常，可以指定多个异常
    // 2. 这里要处理的异常，由程序员来指定
    // 3. Exception e : 表示异常发生后，传递的异常对象
    // 4. Model model : 可以将我们的异常信息，放入 model ，再放入到请求域当中，并传递显示页面

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class,AccessException.class})
    public String handleAritException(Exception e, Model model) {

        log.info("异常信息={}",e.getMessage());  // 自定义的异常也是可以获取到的
        // 这里老师将发生的异常放入到model,可以再错误页面取出显示
        model.addAttribute("msg",e.getMessage());
        return "/error/global";  // 视图地址
    }
}
