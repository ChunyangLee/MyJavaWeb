package com.lichunyang.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatch2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受ServletDispatch1的请求分发，下面代码全部执行
        System.out.println("在Dispatch1中，request设置的域属性"+ request.getAttribute("key"));
        System.out.println("Dispatch2处理自己的业务");
        response.getWriter().write("avvvv");
    }
}
