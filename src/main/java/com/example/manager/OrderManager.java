package com.example.manager;

import com.example.parser.DataParser;
import com.example.parser.ParserFactory;
import com.example.order.Check;
import com.example.order.Order;
import com.example.service.OrderService;

import java.util.List;

public class OrderManager {
     private FileManager fileManager;
     private OrderService orderService;


    public OrderManager(OrderService orderService, FileManager fileManager) {
        this.orderService = orderService;
        this.fileManager = fileManager;
    }

    public void execution(String readPath, String writePath, Double unitPrice, Double currentDiscount, Double step) {

        DataParser dataParser = ParserFactory.create(readPath);


        System.out.println(dataParser);

        List<Order> orders = fileManager.processImport(readPath ,dataParser);


        System.out.println(orders);

        List<Check> сhecks = orderService.calculatePrices(orders, unitPrice, currentDiscount, step);

        fileManager.processExport(сhecks, writePath);


    }


}
