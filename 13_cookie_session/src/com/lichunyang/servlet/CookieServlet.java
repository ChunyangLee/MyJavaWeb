package com.lichunyang.servlet;

import com.lichunyang.utils.CookieUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {

    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("key1", "value1");
        response.addCookie(cookie);

        response.getWriter().write("cookie添加成功！");
    }

    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = CookieUtils.getCookie("key1", request.getCookies());
        response.getWriter().write(cookie.getName()+"="+cookie.getValue());
    }
}
