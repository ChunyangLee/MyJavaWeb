package com.lichunyang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
        System.out.println("1。 Filter的构造器方法");

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Object username = session.getAttribute("username");

        if(username==null){
            //没登陆的话，不让过！ 跳到登陆
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("2。 Filter的初始化方法");
    }

}
