package com.lichunyang.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *      设计的时候，数据结构应该选择链表数组， 因为要进行删除，插入也是最后一位插入， 比arraylist好很多
 */
public class Cart {
    private int totalCount;
    private Double totalPrice;
//    private List<CartItem> items;
    private LinkedHashMap<Integer, CartItem> items;

    public void addCartItem(CartItem item){
        //如果该条目已经存在了
        if(items.containsKey(item.getId())){
            //获得map中的cartItem对象
            CartItem cartItem = items.get(item.getId());
            //更新map中的cartItem数量
            cartItem.setCount(cartItem.getCount()+item.getCount());
        }else{
            items.put(item.getId(), item);
        }

        //不管怎么添加之后，数量和总额都增加了
        this.totalCount +=1;
        this.totalPrice += item.getCount()*item.getPrice();


    }

    public void deleteItemByKey(Integer id){
        CartItem removeItem = this.items.remove(id);
        //删除后，总金额变了,总数量变了
        if(removeItem!=null){
            this.totalPrice -= removeItem.getCount()*removeItem.getPrice();
            this.totalCount -= removeItem.getCount();
        }else {
            System.out.println("无此id的商品！");
        }
    }

    public void clear(){
        this.items.clear();
        this.totalCount=0;
        this.totalPrice=0.0;
    }

    public void updateItemCountById(Integer id, Integer count){
        CartItem cartItem = this.items.get(id);
        //服务器端检验，修改的值的合法性，
        if(count<0){
            System.out.println("修改的数量为负，不合法！");
            return;
        }

        if(cartItem!=null){
            this.totalCount -= cartItem.getCount();
            this.totalPrice -= cartItem.getCount()*cartItem.getPrice();
            cartItem.setCount(count);
            //修改后，总数量和总金额也变化
            this.totalCount += cartItem.getCount();
            this.totalPrice += cartItem.getCount()*cartItem.getPrice();
        }

    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LinkedHashMap<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(LinkedHashMap<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
        //空参构造器，造购物车Cart对象，初始化信息
        this.totalCount=0;
        this.items= new LinkedHashMap<Integer, CartItem>(10);
        this.totalPrice=0.0;
    }
}
