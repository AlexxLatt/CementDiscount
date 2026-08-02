package com.example.service;

import com.example.order.Check;
import com.example.order.Order;

import java.util.List;

public interface CheckCalculator {

    List<Check> calculatePrices(List<Order> orders, double unitPrice, double discount , double step );

}
