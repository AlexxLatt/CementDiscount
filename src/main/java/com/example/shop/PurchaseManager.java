package com.example.shop;

import java.util.List;

public interface PurchaseManager {

    List<Check> calculatePrices(List<Order> orders, double unitPrice, double discount , double step );

}
