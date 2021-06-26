package com.lichunyang.test;

import com.lichunyang.bean.OrderItem;
import com.lichunyang.dao.impl.OrderItemDAOimpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDAOTest {
    OrderItemDAOimpl oidi = new OrderItemDAOimpl();


    @Test
    public void saveOrderItem() {
        oidi.saveOrderItem(new OrderItem(1, "1", "时间简史", 2, 30.));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        System.out.println(oidi.queryOrderItemsByOrderId("1"));

    }

    @Test
    public void removeOrderItemById() {
    }

    @Test
    public void removeOrderItemsByOrderId() {
    }
}