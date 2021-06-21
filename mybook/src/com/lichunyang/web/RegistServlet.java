package com.lichunyang.web;

import com.lichunyang.bean.User;
import com.lichunyang.service.UserService;
import com.lichunyang.service.impl.UserServiceimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        System.out.println(username);
        System.out.println(password);

        if("abcde".equalsIgnoreCase(code)){
            UserService us = new UserServiceimpl();
             if(us.existsUsername(username)){
                 //用户名已存在
                 System.out.println("用户名不可用");
                 request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);

             }else {
                 us.registUser(new User(0, username, password, email));
                 request.getRequestDispatcher("/pages/user/regist_success.html").forward(request, response);

             }

        }else {
            //验证码不正确，返回注册界面
            System.out.println("验证码不正确！");
            request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("mybook的RegistServlet访问测试！");
    }
}
