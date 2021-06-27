package com.lichunyang.dao;

import com.lichunyang.bean.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    /**
     *  将订单加入到数据库中，
     * @param order
     * @return    true： 成功加入返回
     */
    boolean saveOrder(Order order) throws SQLException;

    Order queryOrderByOrderId(String OrderId) throws SQLException;

    List<Order> queryOrdersByUserId(Integer userId) throws SQLException;

    boolean updateOrderByOrderId(String OrderId) throws SQLException;

    boolean removeOrderByOrderId(String orderId) throws SQLException;

    boolean removeOrdersByUserId(Integer userId) throws SQLException;

    /**
     *  管理员，发货，修改订单状态，由订单Id
     * @param status
     * @param orderId
     */
    void changeOrderStatus(int status, String orderId) throws SQLException;

    /**
     *  管理员用， 查询所有订单
     * @return
     */
    List<Order> queryAllOrders() throws SQLException;

}
