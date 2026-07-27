package com.example.processor;

import com.example.adapters.DataParserAdapter;
import com.example.exceptions.FileParserExeption;
import com.example.exceptions.ImportException;
import com.example.shop.OrderInvoice;
import com.example.shop.Order;

import java.io.*;

import com.example.exceptions.ExportException;
import com.example.shop.OrderReceipts;

import java.util.*;

public class FileProcessor implements ProcessorFiles {

    FileParser fileParser = new FileParser();
    DataParserAdapter dataParserAdapter = new DataParserAdapter(fileParser);


    @Override
    public void importFile(OrderReceipts orderReceipts, String filePath, String typeParser) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);

                if (typeParser.equals("|")) {

                    fileParser.parseData(orderReceipts, line);

                } else if (typeParser.equals("#")) {

                    dataParserAdapter.parseData(orderReceipts, line);

                } else {

                    throw new FileParserExeption("Такого типа не существует");
                }
            }


        } catch (IOException e) {
            throw new ImportException("Ошибка при чтении файла" + e.getMessage());
        }
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
