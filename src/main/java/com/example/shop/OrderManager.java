package com.example.shop;

import com.example.processor.FileManager;

import java.util.List;

public class OrderManager {


    public void execution(String readPath, String writePath, Double unitPrice, Double currentDiscount, Double step, FileManager fileManager , OrderService purchase) {

        List<Order> orders = fileManager.processImport(readPath);


        List<Check> сhecks = purchase.calculatePrices(orders, unitPrice, currentDiscount, step);

        fileManager.processExport(сhecks, writePath);


    }


}
