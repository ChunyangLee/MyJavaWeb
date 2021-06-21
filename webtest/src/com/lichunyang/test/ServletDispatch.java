package com.lichunyang.test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletDispatch extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ServletDispatch 将该请求转发到访问src可以么");
//        //不可以，会报错500， 空指针。得不到dispatcher对象！
//        RequestDispatcher dispatcher = request.getRequestDispatcher("../src/1.jpg");
//        if(dispatcher!=null)
//             dispatcher.forward(request, response);
//        else System.out.println("没能得到dispatcher对象！");

        //可以在request里设置attribute， 响应不行。
        request.setAttribute("key", "abvc");
        //响应部分的代码没有执行?
        PrintWriter writer = response.getWriter();
        writer.write("响应可以输出之后在分发！");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/servletDispatch2");
//        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/aaa.html");
        //访问不了图片资源？上面的aaa.html可以
//          RequestDispatcher rd = request.getRequestDispatcher("/1.jpg");

        rd.forward(request, response);
    }
}
