package com.lichunyang.dao;

import com.lichunyang.bean.Order;
import com.lichunyang.bean.OrderItem;

import java.util.List;

public interface OrderItemDAO {
    boolean saveOrderItem(OrderItem orderItem);

    int saveOrderItemsByBatch(List<OrderItem> orderItems);

    List<OrderItem> queryOrderItemsByOrderId(String OrderId);

    boolean removeOrderItemById(Integer OrderItemId);

    boolean removeOrderItemsByOrderId(String orderId);

    boolean updateOrderItemByOrderItemId(Integer id);

}
