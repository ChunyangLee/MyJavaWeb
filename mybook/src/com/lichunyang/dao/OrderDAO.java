package com.lichunyang.dao;

import com.lichunyang.bean.Order;

import java.util.List;

public interface OrderDAO {
    /**
     *  将订单加入到数据库中，
     * @param order
     * @return    true： 成功加入返回
     */
    boolean saveOrder(Order order);

    Order queryOrderByOrderId(String OrderId);

    List<Order> queryOrdersByUserId(Integer userId);

    boolean updateOrderByOrderId(String OrderId);

    boolean removeOrderByOrderId(String orderId);

    boolean removeOrdersByUserId(Integer userId);

    /**
     *  管理员，发货，修改订单状态，由订单Id
     * @param status
     * @param orderId
     */
    void changeOrderStatus(int status, String orderId);

    /**
     *  管理员用， 查询所有订单
     * @return
     */
    List<Order> queryAllOrders();

}
