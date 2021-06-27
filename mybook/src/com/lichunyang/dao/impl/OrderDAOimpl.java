package com.lichunyang.dao.impl;

import com.lichunyang.bean.Order;
import com.lichunyang.dao.BaseDAO;
import com.lichunyang.dao.OrderDAO;
import com.lichunyang.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOimpl extends BaseDAO<Order> implements OrderDAO {
    @Override
    public boolean saveOrder(Order order) throws SQLException {
        Connection con = JdbcUtils.getConnection();
//        Connection con = JdbcUtils.getConnectionByThreadLocal();
        String sql = "insert into t_order(orderId,createTime, price, status,userId) values(?,?,?,?,?)";
        int flag = update(con,sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

        return flag >= 0;
    }

    @Override
    public Order queryOrderByOrderId(String OrderId) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_order where orderId=?";
        Order order = getBean(con, sql, OrderId);
        return order;
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_order where userId=?";
        List<Order> orders = getBeans(con, sql, userId);
        return orders;
    }

    @Override
    public boolean updateOrderByOrderId(String OrderId) throws SQLException {
        System.out.println("待修改方法！");
        return false;
    }

    @Override
    public boolean removeOrderByOrderId(String orderId) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql="delete from t_order where orderId=?";
        int flag = update(con, sql, orderId);
        return flag >= 0;
    }

    @Override
    public boolean removeOrdersByUserId(Integer userId) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql="delete from t_order where userId=?";
        int flag = update(con, sql, userId);
        return flag >= 0;
    }

    @Override
    public void changeOrderStatus(int status, String orderId) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql="update t_order set status=? where orderId=?";
        int flag = update(con, sql, status, orderId);
    }

    @Override
    public List<Order> queryAllOrders() throws SQLException {
        Connection con = JdbcUtils.getConnection();
//        String sql = "select orderId, createTime, price, status_id, userId from t_order";
        String sql = "select orderId, createTime, price, status, userId from t_order";

        List<Order> orders = getBeans(con, sql);
        return orders;
    }
}
