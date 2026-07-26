package com.example.processor;

import com.example.shop.OrderReceipt;

import java.util.List;

public interface DataParser {

    public void parseData(OrderReceipt orderReceipt , List<String> stringData);
}
