package com.example.parser;


import com.example.shop.Order;

public interface DataParser {

    Order parseData(String stringData);
}
