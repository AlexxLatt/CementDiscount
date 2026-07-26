package com.example.processor;

import com.example.adapters.DataParserAdapter;
import com.example.exceptions.FileParserExeption;
import com.example.exceptions.ImportException;
import com.example.shop.OrderInvoice;
import com.example.shop.OrderReceipt;
import com.example.shop.Purchase;

import java.io.*;

import com.example.exceptions.ExportException;

import java.util.*;

public class FileProcessor implements ProcessorFiles {

    FileParser fileParser = new FileParser();
    DataParserAdapter dataParserAdapter = new DataParserAdapter();

    @Override
    public void importFile(OrderReceipt orderReceipt, String filePath, String typeParser) {

        List<String> stringData = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                stringData.add(line);
                System.out.println(line);

                if (typeParser.equals("|")) {

                    fileParser.parseData(orderReceipt, stringData);

                } else if (typeParser.equals("#")) {

                    dataParserAdapter.parseData(orderReceipt, stringData);

                } else {

                    throw new FileParserExeption("Такого типа не существует");
                }
            }


        } catch (IOException e) {
            throw new ImportException("Ошибка при чтении файла");
        }
        System.out.println("Всего строк" + stringData.size());
    }


    @Override
    public void exportFile(OrderInvoice orderInvoice, String filePath) {

        Map<String, Double> purchaseDataSortedByDiscout = orderInvoice.getPurchaseDataSortedByDiscout();

        if (purchaseDataSortedByDiscout.isEmpty() || purchaseDataSortedByDiscout == null) {

            throw new ExportException("В покупах еще не было расчет скидок");
        }

        try (BufferedWriter reader = new BufferedWriter(new FileWriter(filePath))) {

            String line = "";

            for (Map.Entry<String, Double> data : purchaseDataSortedByDiscout.entrySet()) {
                line = data.getKey() + " - " + data.getValue();

                reader.write(line);
                reader.newLine();
            }

        } catch (IOException e) {
            throw new ExportException("Ошибка при записи файла");
        }

    }

}
