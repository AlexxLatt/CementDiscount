package com.example.processor;


import com.example.shop.Order;

public interface DataParser {

    public Order parseData(String stringData);
}
