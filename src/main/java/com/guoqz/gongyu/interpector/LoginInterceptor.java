package com.guoqz.gongyu.interpector;

import com.guoqz.gongyu.bean.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * 使用步骤
 *  1、创建一个类，实现 HandlerInterceptor接口
 *  2、实现接口中的 preHandle方法
 *  3、把拦截器配置到 SpringMVC框架中  需要一个写一个配置类，用配置类来配置拦截器
 *
 *  注意：拦截器会拦截除了jsp文件之外的所有文件（包括静态资源: .css文件、.js文件、.png文件、.jpg文件等）
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 请求到Controller之前要做什么
     * @param request
     * @param response
     * @param handler
     * @return  true放行  false拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // System.out.println("来了一个请求"+uri);

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        // 如果 admin为空，说明用户没用登录     如果登陆了，session会有 admin
        if (admin == null) {
            // 跳转到登录界面
            response.sendRedirect("/index.jsp");
            return false;   // 拦截
        }
        // 放行
        return true;
    }
}
