package com.lichunyang.web;

import com.lichunyang.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserServiceimpl usi = new UserServiceimpl();
        if(!usi.login(username, password)){
            System.out.println("登陆失败！");
            request.getRequestDispatcher("/pages/user/login.html").forward(request, response);
        }else {
            request.getRequestDispatcher("/pages/user/login_success.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
