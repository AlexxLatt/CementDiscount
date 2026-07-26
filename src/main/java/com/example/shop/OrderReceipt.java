package com.example.shop;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class OrderReceipt {

    private Map<LocalDateTime, Map<String, Double>> purchaseData = new TreeMap<>();

    public void setPurchaseData(Map<LocalDateTime, Map<String, Double>> purchaseData) {
        this.purchaseData = purchaseData;
    }

    public Map<LocalDateTime, Map<String, Double>> getPurchaseData() {
        return purchaseData;
    }
}
