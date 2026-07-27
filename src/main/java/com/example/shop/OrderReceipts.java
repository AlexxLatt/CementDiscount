package com.example.shop;

import java.util.ArrayList;
import java.util.List;

public class OrderReceipts {

    List<Order>  orderList = new ArrayList<>();


    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
