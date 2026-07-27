package com.example.adapters;

import com.example.processor.FileParser;
import com.example.shop.Order;
import com.example.shop.OrderReceipts;

public class DataParserAdapter extends FileParser {

    private FileParser fileParser;

    @Override
    public void parseData(OrderReceipts orderReceipts, String stringData) {

        String newTypeStringData = stringData.replaceAll("#", "\\|");
        fileParser.parseData(orderReceipts, newTypeStringData);
    }

    public DataParserAdapter(FileParser fileParser) {
        this.fileParser = fileParser;
    }
}