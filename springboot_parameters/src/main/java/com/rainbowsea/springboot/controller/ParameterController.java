package com.rainbowsea.springboot.controller;


import com.rainbowsea.springboot.bean.Monster;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

//@RestController //  @Controller + @ResponseBody
@Controller
public class ParameterController {

    // 处理添加 monster 的方法
    @PostMapping("/savemonster")
    @ResponseBody
    public String saveMonster(Monster monster) {
        System.out.println("monster-" + monster);
        return "success";
    }

    /**
     * /monster/{id}{name} 构成完整请求路径
     * {id}{name} 就是占位变量
     *
     * @return
     * @PathVariable("name"): 这里 name 和 {name} 命名保持一致
     * String name_ 这里自定义，老师故意这样写下
     * @PatchVArable Map<String, String> map  把所有传递的值传入map
     */


    @GetMapping(value = "/monster/{id}/{name}")
    public String pathVariable(
            @PathVariable("id") String id,
            @PathVariable("name") String name,
            @PathVariable Map<String, String> map
    ) {

        System.out.println("id _" + id);
        System.out.println("name_" + name);
        System.out.println("map_ " + map);
        return "success";
    }


    /**
     * @return
     * @RequestHeader("host" ) String host 获取 http请求头的 host信息
     * @RequestHeader Map<String, String> headers 获取到http 请求的所有信息
     */
    @GetMapping("/requestHeader")
    public String requestHeader(
            @RequestHeader("host") String host,
            @RequestHeader Map<String, String> headers,
            @RequestHeader("accept") String accept

    ) {
        System.out.println("host_" + host);
        System.out.println("header_" + headers);
        System.out.println("accept_" + accept);

        return "success";
    }


    @GetMapping("/hi")
    public String hi(
            @RequestParam(value = "name") String username,
            @RequestParam(value = "fruit") List<String> fruits,// 复选框用数组存储
            @RequestParam Map<String, String> paras
    ) {

        System.out.println("username_" + username);
        System.out.println("fruit_" + fruits);
        System.out.println("paras_" + paras);
        return "success";
    }


    @GetMapping("/cookie")
    public String cookie(
            HttpServletRequest request,
            @CookieValue(value = "cookie_key") String cookie_value,
            @CookieValue(value = "username") Cookie cookie
    ) {
        System.out.println("cookie_value-" + cookie_value);
        if (cookie != null) {

            System.out.println("username-" + cookie);
        }

        System.out.println("--------------------");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie1 : cookies) {
            System.out.println(cookie1.getName() + "=>" + cookie1.getValue());
        }


        return "success";
    }



    @PostMapping("/saves")
    public String postMethod(@RequestBody String content){
        System.out.println(content);
        return "success";
    }


    @GetMapping("/login")
    public String login(
            HttpServletRequest request
    ) {
        request.setAttribute("user", "test");
        request.getSession().setAttribute("website", "http://www.baidu.com");
        return "forward:/ok";  // 转发到 /ok
    }


    @ResponseBody
    @GetMapping("/ok")
    public String ok(
            @RequestAttribute(value = "user") String username,
            @RequestAttribute(value = "website") String website,
            HttpServletRequest request
    ) {

        // 获取到 request域中的数据
        System.out.println("user_" + username);
        System.out.println("website_" + website);
        System.out.println("通过 servlet api 获取 username_" + request.getAttribute("user"));
        System.out.println("通过 servlet api 获取 website_" + request.getSession().getAttribute("website"));

        return "success";
    }



    // 响应一个注册请求
    @GetMapping("/register")
    public String register(Map<String,Object> map,
                           Model model,
                           HttpServletResponse response) {
        // 如果一个注册请求，会将注册数据封装到map或者把Model
        // map中的数据和 model数据，会被放入到request中
        map.put("user","rainbowsea");
        map.put("job","java架构师");
        model.addAttribute("sal",8000);

        // 请求转发
        return "forward:/registerOK";
    }



    @ResponseBody
    @GetMapping("/registerOK")
    public String registerOk(
            HttpServletRequest request
    ){
        System.out.println("user_" + request.getAttribute("user"));
        System.out.println("job_" + request.getAttribute("job"));
        System.out.println("sal-" + request.getAttribute("sal"));
        return "success";
    }



}
