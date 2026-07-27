package com.example.processor;

import com.example.exceptions.FileParserExeption;
import com.example.shop.Order;
import com.example.shop.OrderReceipts;

import java.time.LocalDateTime;
import java.util.*;

public class FileParser implements DataParser {


    @Override
    public void parseData(OrderReceipts orderReceipts, String stringData) {
        if (stringData == null || stringData.isEmpty()) {
            throw new FileParserExeption("Данные не импортировались");
        }

        String[] parts = stringData.split("\\|");
        if (parts.length < 3) {
            throw new FileParserExeption("Недостаточно данных в строке: " + stringData);
        }

        // Создаем новую покупку
        Order order = new Order();
        order.setDate(LocalDateTime.parse(parts[0]));
        order.setCompanyName(parts[1]);
        order.setAmount(Double.parseDouble(parts[2]));

        // Добавляем в переданный OrderReceipts
        orderReceipts.getOrderList().add(order);

        System.out.println("Добавлена покупка: " +
                order.getDate() + " | " +
                order.getCompanyName() + " | " +
                order.getAmount());
    }


}
