package com.example.parser;


import com.example.order.Order;

public interface DataParser {

    Order parseData(String stringData);
}
