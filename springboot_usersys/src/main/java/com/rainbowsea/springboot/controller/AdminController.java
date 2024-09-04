package com.rainbowsea.springboot.controller;


import com.rainbowsea.springboot.bean.Admin;
import com.rainbowsea.springboot.bean.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AdminController {


    @PostMapping("/login")
    public String login(Admin admin, HttpSession session, Model model) {




        // 验证用户是否合法
        if(StringUtils.hasText(admin.getName()) && "666".equals(admin.getPassword())) {
            // 将登录用户保险到 session会话域当中
            session.setAttribute("loginAdmin",admin);

            // 合法，重定向到manage.html
            // 请小伙伴回忆，Java web ，不使用请求转发是防止刷新页面会重复提交
            // 这里老师为什么写的是 manage.html，因为这样可以更加明确的表示到哪个页面
            // manage.html 表示要去找方法的映射路径为: manage.html
            return "redirect:/manage.html";
        } else {
            // 不合法，就重新登录，请求转发
            model.addAttribute("msg","账号/用户错误");
            return "adminLogin";  // 视图解析
        }

    }

    // 处理用户的请求到 manage.html
    // 是重定向——>get
    @GetMapping("/manage.html")
    public String mainPage(Model model,HttpSession session){

        // 可以这里集合~模板数据，放入到request域中，并显示
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(2,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(3,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(4,"关羽","666",28,"gy@ohu.com"));
        users.add(new User(5,"关羽","666",28,"gy@ohu.com"));
        model.addAttribute("users",users);  // 放入到请求域当中
        return "manage";  // 视图解析器

        // 拦截器处理
        // 获取到 session会话域当中的信息，判断用户是否登录过，进行一个过滤
        /*Object loginAdmin = session.getAttribute("loginAdmin");
        if(null != loginAdmin) {  // 说明成功登录过
            // 可以这里集合~模板数据，放入到request域中，并显示
            ArrayList<User> users = new ArrayList<>();
            users.add(new User(1,"关羽","666",28,"gy@ohu.com"));
            users.add(new User(2,"关羽","666",28,"gy@ohu.com"));
            users.add(new User(3,"关羽","666",28,"gy@ohu.com"));
            users.add(new User(4,"关羽","666",28,"gy@ohu.com"));
            users.add(new User(5,"关羽","666",28,"gy@ohu.com"));
            model.addAttribute("users",users);  // 放入到请求域当中

            return "manage";  // 视图解析器
        } else {
            // 说明没有登录过，
            // 这里就返回登录页，并给出提示
            model.addAttribute("msg","你没有登录/请登录");  // 请求域
            return "adminLogin"; // 请求转发到 adminLogin.html视图解析
        }*/


    }
}
