package com.example.manager;

import com.example.shop.Check;
import com.example.shop.Order;

import java.util.List;

public interface CheckCalculator {

    List<Check> calculatePrices(List<Order> orders, double unitPrice, double discount , double step );

}
