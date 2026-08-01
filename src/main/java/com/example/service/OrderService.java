package com.example.service;


import com.example.order.Check;
import com.example.order.Order;

import java.util.*;

public class OrderService implements CheckCalculator {

    @Override
    public List<Check> calculatePrices(List<Order> orders, double unitPrice, double discount, double step) {


        Map<String, Double> purchaseDataSortedByDiscount = new HashMap<>();


        orders.sort(Comparator.comparing(Order::date));

        double currentDiscount = discount;

        orders.stream().forEach(System.out::println);


        for (Order order : orders) {

            String companyName = order.companyName();
            Double amount = order.amount();
            Double price = amount * unitPrice;

            double discountedPrice = price - (price / 100.00 * currentDiscount);


            purchaseDataSortedByDiscount.merge(companyName, discountedPrice, Double::sum);


            currentDiscount -= step;
            if (currentDiscount <= 0) {
                currentDiscount = 0;
            }
        }

        List<Check> checkList = purchaseDataSortedByDiscount.entrySet()
                .stream()
                .map(entry -> new Check(entry.getKey(), entry.getValue()))
                .toList();


        return checkList;
    }


}
