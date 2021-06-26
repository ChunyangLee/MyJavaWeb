package com.lichunyang.web;

import com.lichunyang.bean.Cart;
import com.lichunyang.bean.Order;
import com.lichunyang.bean.OrderItem;
import com.lichunyang.bean.User;
import com.lichunyang.service.impl.OrderServiceimpl;
import com.lichunyang.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class OrderServlet extends BaseServlet {
    OrderServiceimpl osi = new OrderServiceimpl();
    UserServiceimpl usi = new UserServiceimpl();

    private Integer getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        User user = usi.getUsersByLastName(username).get(0);
        return user.getId();
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        Order order = osi.createOrder(getUserId(request, response), cart);
        //订单生成后清空购物车
        cart.clear();

        //创建订单后，请求重定向到checkout.jsp
        response.sendRedirect("/mybook/pages/cart/checkout.jsp?orderId=" + order.getOrderId());

    }



    protected void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> orders = osi.showOrders(getUserId(request, response));
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderItem> orderItems = osi.showOrderDetails((String) request.getParameter("orderId"));
        double sum = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();

        request.setAttribute("orderItems", orderItems);
        request.setAttribute("totalPrice", sum);

        request.getRequestDispatcher("pages/order/orderDetail.jsp").forward(request, response);
    }

}




