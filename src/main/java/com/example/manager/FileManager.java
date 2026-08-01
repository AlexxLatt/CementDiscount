package com.example.manager;

import com.example.exceptions.ExportException;
import com.example.exceptions.ImportException;
import com.example.parser.DataParser;
import com.example.order.Check;
import com.example.order.Order;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileManager {

    public List<Order> processImport(String filePath, DataParser dataParser) {



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



    public static void processExport(List<Check> checks, String filePath) {


        if (checks == null || checks.isEmpty()) {

            throw new ExportException("В покупах еще не было расчет скидок");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            String line = "";

            for (Check check : checks) {
                line = check.companyName() + " | " + check.price();

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new ExportException("Ошибка при записи файла");
        }
    }
}
