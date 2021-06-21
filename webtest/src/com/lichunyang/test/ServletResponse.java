package com.lichunyang.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletResponse extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type","text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print("中文问题");

        //响应重定向，上面的代码也不执行了。
//        response.setStatus(302);
//        response.setHeader("Location", "http://localhost:8080/book/servletResponse2");

        response.sendRedirect("http://localhost:8080/book/servletResponse2");
    }
}
