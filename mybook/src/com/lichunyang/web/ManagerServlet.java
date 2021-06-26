package com.lichunyang.web;

import com.lichunyang.bean.Order;
import com.lichunyang.service.impl.OrderServiceimpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagerServlet extends BaseServlet {
    OrderServiceimpl osi= new OrderServiceimpl();

    protected void orderManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("orderManage指ing");
        List<Order> orders = osi.showAllOrdersForAdmin();
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("../pages/manager/order_manager.jsp").forward(request, response);

    }

    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("发货操作！");
        osi.sendOrder(request.getParameter("orderId"));

        response.sendRedirect("/mybook/manager/managerServlet?action=orderManage");
    }

}
