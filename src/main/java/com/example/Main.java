package com.example;

import com.example.manager.OrderManager;
import com.example.manager.OrderService;
import com.example.manager.FileManager;


public class Main {
    public static void main(String[] args) {

        OrderManager orderManager = new OrderManager();

        FileManager fileManager = new FileManager();
        OrderService orderService = new OrderService();

        orderManager.execution("src/main/resources/discount_day.txt", "src/main/resources/test1.txt", 5.00, 50.00, 5.0, fileManager , orderService);
        orderManager.execution("src/main/resources/discount_day_without_ext", "src/main/resources/test2.txt", 5.00, 50.00, 5.0, fileManager ,orderService);

    }
}
