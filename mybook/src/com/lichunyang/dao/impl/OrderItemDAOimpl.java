package com.lichunyang.dao.impl;

import com.lichunyang.bean.OrderItem;
import com.lichunyang.dao.BaseDAO;
import com.lichunyang.dao.OrderItemDAO;
import com.lichunyang.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class OrderItemDAOimpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public boolean saveOrderItem(OrderItem orderItem) {
        Connection con = JdbcUtils.getConnection();
        String sql = "insert into t_orderItem(name,count,price,totalPrice,orderId) values(?,?,?,?,?)";
        int flag = update(con, sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(),
                orderItem.getPrice() * orderItem.getCount(), orderItem.getOrderId());
        JdbcUtils.close(con);
        return flag>=0;
    }

    @Override
    public int saveOrderItemsByBatch(List<OrderItem> orderItems) {
        Connection con = JdbcUtils.getConnection();
        String sql = "insert into t_orderItem(name,count,price,totalPrice,orderId) values(?,?,?,?,?)";
        Object[][] params = new Object[orderItems.size()][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[]{orderItems.get(i).getName(),orderItems.get(i).getCount(),orderItems.get(i).getPrice(),
                    orderItems.get(i).getCount()*orderItems.get(i).getPrice(),orderItems.get(i).getOrderId()};
        }
        int result = updateByBatch(con,sql,params);
        JdbcUtils.close(con);
        return result;
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String OrderId) {
        Connection con = JdbcUtils.getConnection();
        String sql="select * from t_orderItem where orderId = ?";
        List<OrderItem> orderItems = getBeans(con, sql, OrderId);
        JdbcUtils.close(con);
        return orderItems;
    }

    @Override
    public boolean removeOrderItemById(Integer OrderItemId) {
        Connection con = JdbcUtils.getConnection();
        String sql = "delete from t_orderItem where id=?";
        int flag = update(con, sql, OrderItemId);
        JdbcUtils.close(con);
        return flag>=0;
    }

    @Override
    public boolean removeOrderItemsByOrderId(String orderId) {
        Connection con = JdbcUtils.getConnection();
        String sql = "delete from t_orderItem where orderId=?";
        int flag = update(con, sql, orderId);
        JdbcUtils.close(con);
        return flag>=0;
    }

    @Override
    public boolean updateOrderItemByOrderItemId(Integer id) {
        System.out.println("未完成功能，后续可能管理员使用，修改订单的订单项");
        return false;
    }
}
