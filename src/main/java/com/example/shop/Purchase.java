package com.example.shop;

import java.time.LocalDateTime;
import java.util.*;

public class Purchase implements PurchaseManager {


    Map<String, LocalDateTime> lastDateOfPurchase = new TreeMap<>();


    @Override
    public void sortPurchaseByDiscount(OrderInvoice orderInvoice,OrderReceipt orderReceipt ,double discount) {

        Map<String, Double> purchaseDataSortedByDiscout = orderInvoice.getPurchaseDataSortedByDiscout();
        Map<LocalDateTime, Map<String, Double>> purchaseData = orderReceipt.getPurchaseData();

        double currentDiscount = discount;

        for (Map.Entry<LocalDateTime, Map<String, Double>> data : purchaseData.entrySet()) {
            LocalDateTime purchaseDate = data.getKey();
            Map<String, Double> innerMap = data.getValue();

            for (Map.Entry<String, Double> company : innerMap.entrySet()) {
                String companyName = company.getKey();
                Double price = company.getValue();

                double discountedPrice = price - (price / 100 * currentDiscount);


                purchaseDataSortedByDiscout.merge(companyName, discountedPrice, Double::sum);

                LocalDateTime existingDate = lastDateOfPurchase.get(companyName);

                if (existingDate == null || purchaseDate.isAfter(existingDate)) {
                    lastDateOfPurchase.put(companyName, purchaseDate);
                }

                currentDiscount -= 5;
                if (currentDiscount <= 0) {
                    currentDiscount = 0;
                }
            }
        }

        orderInvoice.setPurchaseDataSortedByDiscout(purchaseDataSortedByDiscout);

        for (Map.Entry<String, Double> entry : purchaseDataSortedByDiscout.entrySet()) {
            System.out.println(entry.getKey() + " | последняя покупка: " +
                    lastDateOfPurchase.get(entry.getKey()) + " | сумма: " +
                    entry.getValue());
        }


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
