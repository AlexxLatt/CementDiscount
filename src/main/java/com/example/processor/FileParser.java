package com.example.processor;

import com.example.exceptions.FileParserExeption;
import com.example.shop.Order;

import java.time.LocalDateTime;

public class FileParser implements DataParser {


    @Override
    public Order parseData(String stringData) {
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


        System.out.println("Добавлена покупка: " +
                order.getDate() + " | " +
                order.getCompanyName() + " | " +
                order.getAmount());

        return order;
    }


}
