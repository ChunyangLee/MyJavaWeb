package com.lichunyang.dao;

import com.lichunyang.bean.Order;
import com.lichunyang.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDAO {
    boolean saveOrderItem(OrderItem orderItem) throws SQLException;

    int saveOrderItemsByBatch(List<OrderItem> orderItems) throws SQLException ;

    List<OrderItem> queryOrderItemsByOrderId(String OrderId)throws SQLException ;

    boolean removeOrderItemById(Integer OrderItemId)throws SQLException ;

    boolean removeOrderItemsByOrderId(String orderId)throws SQLException ;

    boolean updateOrderItemByOrderItemId(Integer id)throws SQLException ;

}
