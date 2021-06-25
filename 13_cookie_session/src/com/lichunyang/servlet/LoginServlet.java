package com.lichunyang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends BaseServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.getWriter().write("判断用户名密码");
        response.getWriter().write("username="+username+", password="+password);
        response.getWriter().write("假定用户名和密码是正确的"+"<br/>");
        response.getWriter().write("密码是正确的之后我们把用户名和密码保存在cookie发送给客户端，以便下次客户端浏览器在访问时，我们可以获取cookie，从而可以直接把用户名和密码填充！"+"<br/>");

        //发送Cookie给客户端
        Cookie username1 = new Cookie("username", username);
        Cookie password1 = new Cookie("password", password);
        response.addCookie(username1);
        response.addCookie(password1);


    }

}
