package com.lichunyang.test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class HelloServlet implements Servlet {
    public HelloServlet(){
        System.out.println("1。 构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化init执行！");
        System.out.println(servletConfig.getServletName());

        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            System.out.println(initParameterNames.nextElement());
        }

        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println(servletContext);

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service方法，处理请求，同时相应数据!");
        String method = ((HttpServletRequest) servletRequest).getMethod();
        if("POST".equals(method)){
            System.out.println("post请求接收到！");
        }else if("GET".equals(method)){
            System.out.println("get请求接收到！");
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
