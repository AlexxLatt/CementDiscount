package com.example.processor;


import com.example.exceptions.ImportException;
import com.example.shop.Order;


import java.io.*;

import java.util.*;

public class ImportProcessor implements ProcessorFiles {


    private DataParser dataParser;

    public ImportProcessor(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public List<Order> importFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;


            List<Order> orders = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                orders.add(dataParser.parseData(line));
            }

            return orders;
        } catch (IOException e) {

            throw new ImportException("Ошибка при чтении файла: " + e.getMessage());
        }
    }


}
