package com.lichunyang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet {
    protected void session(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean aNew = session.isNew();
        String id = session.getId();

        response.getWriter().write(""+session+"<br/>");
        response.getWriter().write(""+aNew+"<br/>");
        response.getWriter().write(id+"<br/>");
    }

    protected void lifeControl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.setMaxInactiveInterval(3);

        response.getWriter().write("当前session设置3s失效，");

    }

}
