package com.example.parser;

import com.example.order.Order;


public class DataParserAdapter implements DataParser {
    private final DataParser dataParser;

    public DataParserAdapter(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public Order parseData(String stringData) {

        String newTypeStringData = stringData.replaceAll("#", "|");
        return dataParser.parseData(newTypeStringData);

    }

    @Override
    public String toString() {
        return "DataParserAdapter{}";
    }
}