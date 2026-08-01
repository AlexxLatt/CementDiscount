package com.example.manager;

import com.example.parser.DataParser;
import com.example.parser.ParserFactory;
import com.example.order.Check;
import com.example.order.Order;
import com.example.service.OrderService;

import java.util.List;

public class OrderManager {


    public void execution(String readPath, String writePath, Double unitPrice, Double currentDiscount, Double step, FileManager fileManager , OrderService purchase) {

        DataParser dataParser = ParserFactory.create(readPath);


        System.out.println(dataParser);

        List<Order> orders = fileManager.processImport(readPath ,dataParser);


        List<Check> сhecks = purchase.calculatePrices(orders, unitPrice, currentDiscount, step);

        fileManager.processExport(сhecks, writePath);


    }


}
