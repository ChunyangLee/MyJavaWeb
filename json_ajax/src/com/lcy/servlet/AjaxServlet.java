package com.lcy.servlet;

import com.google.gson.Gson;
import com.lcy.bean.Person;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends BaseServlet {
    protected void ajax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("已经收到ajax请求");

        Person p1 = new Person(1, "徐胜男");
        Gson gson = new Gson();
        String personJson = gson.toJson(p1);

        response.getWriter().write(personJson);
    }

    protected void jQueryAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jQueryAjax已经收到");

        Person p1 = new Person(1, "徐胜男");
        Gson gson = new Gson();
        String personJson = gson.toJson(p1);

        response.getWriter().write(personJson);
    }

    protected void serialize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("serialize method!");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String single = request.getParameter("single");

        System.out.println(username);
        Gson gson = new Gson();

//        response.getWriter().write();
    }

}
