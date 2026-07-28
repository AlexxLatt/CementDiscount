package com.example.shop;

import java.time.LocalDateTime;
import java.util.*;

public class Purchase implements PurchaseManager {


    Map<String, LocalDateTime> lastDateOfPurchase = new TreeMap<>();


    @Override
    public Сhecks sortPurchaseByDiscount(List<Order> orders, double discount) {

        Сhecks сhecks = new Сhecks();
        Map<String, Double> purchaseDataSortedByDiscount = new HashMap<>();
        Map<String, LocalDateTime> lastDateOfPurchase = new HashMap<>();

        orders.sort(Comparator.comparing(Order::getDate));

        double currentDiscount = discount;


        for (Order order : orders) {
            String companyName = order.getCompanyName();
            Double price = order.getAmount();
            LocalDateTime purchaseDate = order.getDate();

            double discountedPrice = price - (price / 100 * currentDiscount);
            purchaseDataSortedByDiscount.merge(companyName, discountedPrice, Double::sum);

            LocalDateTime existingDate = lastDateOfPurchase.get(companyName);
            if (existingDate == null || purchaseDate.isAfter(existingDate)) {
                lastDateOfPurchase.put(companyName, purchaseDate);
            }

            currentDiscount -= 5;
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


    public Map<String, LocalDateTime> getLastDateOfPurchase() {
        return lastDateOfPurchase;
    }

    public void setLastDateOfPurchase(Map<String, LocalDateTime> lastDateOfPurchase) {
        this.lastDateOfPurchase = lastDateOfPurchase;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "lastDateOfPurchase=" + lastDateOfPurchase +
                '}';
    }
}
