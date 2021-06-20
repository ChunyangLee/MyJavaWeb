package com.lichunyang.test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatch extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDispatch 将该请求转发到访问src可以么");
        //不可以，会报错500， 空指针。得不到dispatcher对象！
        RequestDispatcher dispatcher = request.getRequestDispatcher("../src/1.jpg");
        if(dispatcher!=null)
             dispatcher.forward(request, response);
        else System.out.println("没能得到dispatcher对象！");
    }
}
