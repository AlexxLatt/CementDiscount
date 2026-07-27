package com.example.processor;


import com.example.shop.OrderReceipts;


public interface DataParser {

    public void parseData(OrderReceipts orderReceipts, String stringData);
}
