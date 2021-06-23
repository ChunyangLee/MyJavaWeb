package com.lichunyang.web;

import com.lichunyang.bean.User;
import com.lichunyang.service.UserService;
import com.lichunyang.service.impl.UserServiceimpl;
import com.lichunyang.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserServiceimpl usi = new UserServiceimpl();
        if(!usi.login(username, password)){
            System.out.println("登陆失败！");
            request.setAttribute("msg", "用户名或密码错误！");
            //用户回显数据，错了，也要在显示给用户
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        }else {
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = WebUtils.copyParamsToBean(request.getParameterMap(), new User());

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if("abcde".equalsIgnoreCase(code)){
            UserService us = new UserServiceimpl();
            if(us.existsUsername(username)){
                //用户名已存在
                System.out.println("用户名不可用");
                request.setAttribute("msg", "用户名不可用");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("code", code);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            }else {
                us.registUser(user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }

        }else {
            //验证码不正确，返回注册界面
            request.setAttribute("msg", "验证码不正确！");
            System.out.println("验证码不正确！");
            request.setAttribute("code", code);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

}
