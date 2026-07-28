package com.example.adapters;

import com.example.processor.FileParser;
import com.example.shop.Order;


public class DataParserAdapter extends FileParser {
    @Override
    public Order parseData(String stringData) {

        String newTypeStringData = stringData.replaceAll("#", "\\|");
        return super.parseData(newTypeStringData);

    }
}