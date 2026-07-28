package com.example.processor;

import com.example.shop.Order;

import java.util.List;


public class ImportManager {

    public List<Order> processImport(String filePath) {


        DataParser dataParser = ParserFactory.create(filePath);

        ImportProcessor fileProcessor = new ImportProcessor(dataParser);

        return fileProcessor.importFile(filePath);

    }

}
