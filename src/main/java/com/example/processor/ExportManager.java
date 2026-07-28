package com.example.processor;

import com.example.exceptions.ExportException;
import com.example.shop.Сhecks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ExportManager {

    public static void processExport(Сhecks checks, String filePath) {
        Map<String, Double> purchaseDataSortedByDiscout = checks.getcompanyCheckAmounts();


        if (purchaseDataSortedByDiscout == null || purchaseDataSortedByDiscout.isEmpty()) {

            throw new ExportException("В покупах еще не было расчет скидок");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            String line = "";

            for (Map.Entry<String, Double> data : purchaseDataSortedByDiscout.entrySet()) {
                line = data.getKey() + " - " + data.getValue();

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new ExportException("Ошибка при записи файла");
        }
    }
}