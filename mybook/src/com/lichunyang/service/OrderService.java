package com.lichunyang.service;

import com.lichunyang.bean.Cart;
import com.lichunyang.bean.Order;
import com.lichunyang.bean.OrderItem;

import java.util.List;

public interface OrderService {
    /**
     *   由指定对象的购物车信息生成属于该用户的订单，
     * @param userId   用户ID
     * @param cart     用户的购物车，
     */
    Order createOrder(Integer userId, Cart cart);

    /**
     *  展示指定用户Id的所有订单
     * @param userId
     * @return
     */
    List<Order> showOrders(Integer userId);

    /**
     *      管理员用，展示所有订单
     * @return
     */
    List<Order> showAllOrdersForAdmin();

    /**
     *   管理员用，根据指定订单Id修改订单
     * @param orderId
     */
    void updateOrderById(String orderId);

    boolean remove(String orderId);

    /**
     *  展示订单详情，里面的订单项条目
     * @param orderId
     */
    List<OrderItem> showOrderDetails(String orderId);

    void receiveOrder(String orderId);

    void sendOrder(String orderId);
}
