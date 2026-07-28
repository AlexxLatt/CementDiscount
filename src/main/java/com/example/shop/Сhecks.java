package com.example.shop;

import java.util.Map;
import java.util.TreeMap;

public class Сhecks {

    Map<String, Double> companyCheckAmounts = new TreeMap<>();


    public Map<String, Double> getcompanyCheckAmounts() {
        return companyCheckAmounts;
    }

    public void setСompanyCheckAmounts(Map<String, Double> companyCheckAmounts) {
        this.companyCheckAmounts = companyCheckAmounts;
    }
}
