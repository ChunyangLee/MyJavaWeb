package com.lichunyang.test;

import com.lichunyang.bean.Constants;
import com.lichunyang.bean.Order;
import com.lichunyang.dao.impl.OrderDAOimpl;
import org.junit.Test;

import java.sql.Date;
import java.time.Instant;

public class OrderDAOTest {

    @Test
    public void saveOrder() {
        OrderDAOimpl odi = new OrderDAOimpl();
        Order order = new Order();
        order.setPrice(100.0);
        order.setUserId(3);
        order.setCreateTime(new Date(Instant.now().toEpochMilli()));
        order.setOrderId(String.valueOf(Instant.now().toEpochMilli()));
        odi.saveOrder(order);
    }

    @Test
    public void queryOrderByOrderId() {
        OrderDAOimpl odi = new OrderDAOimpl();
        Order order = odi.queryOrderByOrderId("2");
        System.out.println(order);
    }

    @Test
    public void queryOrdersByUserId() {
        OrderDAOimpl odi = new OrderDAOimpl();
        odi.queryOrdersByUserId(2).forEach(System.out::println);

    }

    @Test
    public void updateOrderByOrderId() {
        OrderDAOimpl odi = new OrderDAOimpl();

    }

    @Test
    public void removeOrderByOrderId() {
        OrderDAOimpl odi = new OrderDAOimpl();
        System.out.println(odi.removeOrderByOrderId("3"));
    }

    @Test
    public void removeOrdersByUserId() {
        OrderDAOimpl odi = new OrderDAOimpl();
        System.out.println(odi.removeOrdersByUserId(4));
    }

    @Test
    public void changeOrderStatus() {
        OrderDAOimpl odi = new OrderDAOimpl();
        odi.changeOrderStatus(Constants.DELIVERED,"2");
    }

    @Test
    public void queryAllOrders() {
        OrderDAOimpl odi = new OrderDAOimpl();
        System.out.println(odi.queryAllOrders());
    }
}