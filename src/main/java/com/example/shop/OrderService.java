package com.example.shop;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService implements PurchaseManager {

    @Override
    public Сhecks calculatePrices(List<Order> orders, double unitPrice, double discount, double step) {

        Сhecks сhecks = new Сhecks();
        Map<String, Double> purchaseDataSortedByDiscount = new HashMap<>();
        Map<String, LocalDateTime> lastDateOfPurchase = new HashMap<>();

        orders.sort(Comparator.comparing(Order::date));

        double currentDiscount = discount;


        for (Order order : orders) {

            String companyName = order.companyName();
            Double amount = order.amount();
            Double price = amount * unitPrice;
            LocalDateTime purchaseDate = order.date();


            double discountedPrice = price - (price / 100.00 * currentDiscount);
            purchaseDataSortedByDiscount.merge(companyName, discountedPrice, Double::sum);

            LocalDateTime existingDate = lastDateOfPurchase.get(companyName);
            if (existingDate == null || purchaseDate.isAfter(existingDate)) {
                lastDateOfPurchase.put(companyName, purchaseDate);
            }

            currentDiscount -= step;
            if (currentDiscount <= 0) {
                currentDiscount = 0;
            }
        }


        for (Map.Entry<String, Double> entry : purchaseDataSortedByDiscount.entrySet()) {
            System.out.println(entry.getKey() + " | последняя покупка: " +
                    lastDateOfPurchase.get(entry.getKey()) + " | сумма: " +
                    entry.getValue());
        }

        сhecks.setСompanyCheckAmounts(purchaseDataSortedByDiscount);


        return сhecks;
    }


}
