package com.lichunyang.service.impl;

import com.lichunyang.bean.*;
import com.lichunyang.dao.impl.BookDAOimpl;
import com.lichunyang.dao.impl.OrderDAOimpl;
import com.lichunyang.dao.impl.OrderItemDAOimpl;
import com.lichunyang.service.OrderService;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderServiceimpl implements OrderService {
       OrderDAOimpl odi =  new OrderDAOimpl();
       OrderItemDAOimpl oidi = new OrderItemDAOimpl();
        BookDAOimpl bdi = new BookDAOimpl();

    @Override
    public Order createOrder(Integer userId, Cart cart) {
        Order order = new Order();

        order.setPrice(cart.getTotalPrice());
        order.setUserId(userId);
        order.setCreateTime(new Date(Instant.now().toEpochMilli()));
        order.setOrderId(String.valueOf(Instant.now().toEpochMilli()));

        odi.saveOrder(order);

        //购物车每一个条目都封装成OrderItem，插入数据库
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem:cart.getItems().values()){
            orderItems.add(new OrderItem(cartItem.getId(), order.getOrderId(), cartItem.getName(),cartItem.getCount(),cartItem.getPrice() ));
            //生成订单后，库存和销量要减少, 先找到图书，修改完成后，在更新数据库
            List<Book> books = bdi.queryByBookName(cartItem.getName());
            books.get(0).setSales( books.get(0).getSales()+cartItem.getCount());
            books.get(0).setStock( books.get(0).getStock()-cartItem.getCount());
            bdi.updateBookByName( books.get(0),books.get(0).getName());

        }

        int result = oidi.saveOrderItemsByBatch(orderItems);



        return order;
    }

    @Override
    public List<Order> showOrders(Integer userId) {
       return odi.queryOrdersByUserId(userId);
    }

    @Override
    public List<Order> showAllOrdersForAdmin() {
        return odi.queryAllOrders();
    }

    @Override
    public void updateOrderById(String orderId) {
        System.out.println("尚未提供的功能！");
    }

    @Override
    public boolean remove(String orderId) {
        return odi.removeOrderByOrderId(orderId);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return oidi.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public void receiveOrder(String orderId) {
        odi.changeOrderStatus(Constants.RECEIVED,orderId);
    }

    @Override
    public void sendOrder(String orderId) {
        odi.changeOrderStatus(Constants.DELIVERED,orderId);
    }
}
