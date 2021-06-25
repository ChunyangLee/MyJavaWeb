package com.lichunyang.web;

import com.lichunyang.bean.User;
import com.lichunyang.service.UserService;
import com.lichunyang.service.impl.UserServiceimpl;
import com.lichunyang.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserServiceimpl usi = new UserServiceimpl();
        if(!usi.login(username, password)){
//            System.out.println("登陆失败！");
            request.setAttribute("msg", "用户名或密码错误！");
            //用户回显数据，错了，也要在显示给用户
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        }else {
            //new added on 2021.6.25
            System.out.println(request.getSession().getId());
            System.out.println(request.getSession().isNew());
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);
            //
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = WebUtils.copyParamsToBean(request.getParameterMap(), new User());

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //验证码自动被放到session域中
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //取出后删除域中的验证码防止被重复使用
        request.removeAttribute(KAPTCHA_SESSION_KEY);

        if(token!=null && token.equalsIgnoreCase(code)){
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
                //使用转发请求，会出现重复提交表单，因为刷新浏览器， 浏览器会自动提交记录的最后一次请求，
//                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
                response.sendRedirect("/mybook/pages/user/regist_success.jsp");
            }
        }else{
            //验证码不正确，返回注册界面
            //放入request域中回显
            request.setAttribute("msg", "验证码不正确！");
            System.out.println("验证码不正确！");
            request.setAttribute("code", code);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        System.out.println(session.getId());
        if(!session.isNew()){
            session.invalidate();
        }
        response.sendRedirect("/index.jsp");
    }


}
