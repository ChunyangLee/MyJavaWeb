package com.lichunyang.web;

import com.lichunyang.bean.Cart;
import com.lichunyang.bean.Order;
import com.lichunyang.bean.OrderItem;
import com.lichunyang.bean.User;
import com.lichunyang.service.impl.OrderServiceimpl;
import com.lichunyang.service.impl.UserServiceimpl;
import com.lichunyang.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class OrderServlet extends BaseServlet {
    OrderServiceimpl osi = new OrderServiceimpl();
    UserServiceimpl usi = new UserServiceimpl();

    private Integer getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        User user = null;
        try {
            user = usi.getUsersByLastName(username).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user.getId();
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        Order order = null;
        //生成订单后，可能有别的操作比如付款，当出现异常时，则订单生成了，但是没付款，订单要回滚。不能插入到数据库，以及修改商品的库存信息
        //因此必须要在servlet程序里面，处理异常，异常要一直抛

            order = osi.createOrder(getUserId(request, response), cart);

            int i=12/0;   //测试生成订单后出异常，
            //订单生成后清空购物车
            cart.clear();

            //创建订单后，请求重定向到checkout.jsp
            response.sendRedirect("/mybook/pages/cart/checkout.jsp?orderId=" + order.getOrderId());



    }



    protected void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        List<Order> orders = null;
        orders = osi.showOrders(getUserId(request, response));
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderItem> orderItems = null;
        try {
            orderItems = osi.showOrderDetails((String) request.getParameter("orderId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        double sum = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();

        request.setAttribute("orderItems", orderItems);
        request.setAttribute("totalPrice", sum);

        request.getRequestDispatcher("pages/order/orderDetail.jsp").forward(request, response);
    }

}




