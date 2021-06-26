package com.lichunyang.bean;

import java.sql.Date;

public class Order {
    private String orderId;
    private Date createTime;
    private Double price;
    private Integer userId;
    private int status=Constants.UNDELIVERED;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }

    public Order() {
    }

    public Order(String orderId, Date createTime, Double price, Integer userId, int status) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.userId = userId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
