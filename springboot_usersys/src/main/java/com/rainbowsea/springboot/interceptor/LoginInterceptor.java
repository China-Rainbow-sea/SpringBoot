package com.rainbowsea.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 为了让大家看到访问的URI
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();
        log.info("preHandle 拦截到的请求URI={}", requestURI);
        log.info("preHandle 拦截到的请求URL={}", requestURL);

        // 进行登录的校验
        HttpSession session = request.getSession();
        Object loginAdmin = session.getAttribute("loginAdmin");
        if (null != loginAdmin) { // 说明该用户已经成功登录
            // 返回 true 就是放行
            return true;
        } else {
            // 拦截，重新返回到登录页面
            request.setAttribute("msg", "你没有登录/请登录~~~");
            // 注意：因为这里我们只有一个内容被拦截了，而且该内容的 uri路径就是我们要跳转进入的路径
            request.getRequestDispatcher("/").forward(request, response);  // 重定向
            return false; // 拦截了，不放行

        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle执行了...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion执行了...");

    }
}
// HandlerInterceptor