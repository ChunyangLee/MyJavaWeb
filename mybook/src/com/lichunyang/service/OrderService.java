package com.lichunyang.service;

import com.lichunyang.bean.Cart;
import com.lichunyang.bean.Order;
import com.lichunyang.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    /**
     *   由指定对象的购物车信息生成属于该用户的订单，
     * @param userId   用户ID
     * @param cart     用户的购物车，
     */
    Order createOrder(Integer userId, Cart cart) throws SQLException;

    /**
     *  展示指定用户Id的所有订单
     * @param userId
     * @return
     */
    List<Order> showOrders(Integer userId) throws SQLException;

    /**
     *      管理员用，展示所有订单
     * @return
     */
    List<Order> showAllOrdersForAdmin() throws SQLException;

    /**
     *   管理员用，根据指定订单Id修改订单
     * @param orderId
     */
    void updateOrderById(String orderId) throws SQLException;

    boolean remove(String orderId) throws SQLException;

    /**
     *  展示订单详情，里面的订单项条目
     * @param orderId
     */
    List<OrderItem> showOrderDetails(String orderId) throws SQLException;

    void receiveOrder(String orderId) throws SQLException;

    void sendOrder(String orderId) throws SQLException;
}
