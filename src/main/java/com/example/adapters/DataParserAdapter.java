package com.example.adapters;

import com.example.parser.DataParser;
import com.example.shop.Order;


public class DataParserAdapter implements DataParser {
    private final DataParser dataParser;

    public DataParserAdapter(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public Order parseData(String stringData) {

        String newTypeStringData = stringData.replaceAll("#", "\\|");
        return dataParser.parseData(newTypeStringData);

    }
}