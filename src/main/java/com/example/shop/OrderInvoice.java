package com.example.shop;

import java.util.Map;
import java.util.TreeMap;

public class OrderInvoice {

    Map<String, Double> purchaseDataSortedByDiscout = new TreeMap<>();


    public Map<String, Double> getPurchaseDataSortedByDiscout() {
        return purchaseDataSortedByDiscout;
    }

    public void setPurchaseDataSortedByDiscout(Map<String, Double> purchaseDataSortedByDiscout) {
        this.purchaseDataSortedByDiscout = purchaseDataSortedByDiscout;
    }
}
