package com.example.parser;

import com.example.exceptions.FileParserExeption;
import com.example.shop.Order;

import java.time.LocalDateTime;

import static java.lang.Double.parseDouble;

public class DataParserImpl implements DataParser {


    @Override
    public Order parseData(String stringData) {
        if (stringData == null || stringData.isEmpty()) {
            throw new FileParserExeption("Данные не импортировались");
        }

        String[] parts = stringData.split("\\|");
        if (parts.length < 3) {
            throw new FileParserExeption("Недостаточно данных в строке: " + stringData);
        }

        Order order = new Order(LocalDateTime.parse(parts[0]),parts[1], Double.parseDouble(parts[2]));

        System.out.println("Добавлена покупка: " +
                order.date() + " | " +
                order.companyName() + " | " +
                order.amount());

        return order;
    }


}
