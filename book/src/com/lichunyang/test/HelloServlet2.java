package com.lichunyang.test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("自动生成的doPost方法。");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("自动生成的doGet方法。");
        //context-param 整个web工程公用的，每个servlet都可以获取
        ServletContext context = getServletConfig().getServletContext();
        String username = context.getInitParameter("username");
        System.out.println(username);

        System.out.println("当前工程路径: "+ context.getContextPath());
        System.out.println("工程的绝对路径"+context.getRealPath("/"));


    }
}
