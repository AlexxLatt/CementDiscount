package Processor;

import Exceptions.FileParserExeption;
import Exceptions.ImportException;
import Interfaces.DataParser;
import Shop.Purchase;

import java.io.*;
import Exceptions.ExportException;
import java.time.LocalDateTime;
import java.util.*;

public class FileProcessor implements Interfaces.FileProcessor, DataParser {




    @Override
    public void parseData(Purchase purchase) {

        List<String> stringData = purchase.getPurchaseStringData();
        List<String[]> parsedData = new ArrayList();

        Map<LocalDateTime, Map<String, Double>> finalData = new TreeMap<>();

        LocalDateTime purchaseDate ;
        String companyName;
        double purchaseAmount;


        int index = 0;
        if (stringData.isEmpty() || stringData == null) {
            throw new FileParserExeption("Данные не импортирвались");


        } else {
            for (String data : stringData) {

                parsedData.add(data.split("\\|"));
                Map<String, Double> purchaseCompany = new HashMap<>();


                purchaseDate = LocalDateTime.parse(parsedData.get(index)[0]);
                companyName = parsedData.get(index)[1];
                purchaseAmount = Double.parseDouble(parsedData.get(index)[2]);

                purchaseCompany.put(companyName, purchaseAmount);
                finalData.put(purchaseDate, purchaseCompany);

                index++;

            }
            purchase.setPurchaseData(finalData);

            System.out.println("parsedData:");
            for (String[] arr : parsedData) {
                System.out.println(Arrays.toString(arr)); // Выводит содержимое каждого массива
            }
        }


    }

    @Override
    public void ImportFile(Purchase purchase, String filePath) {
        List<String> stringData = purchase.getPurchaseStringData();


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                stringData.add(line);
                System.out.println(line);
            }

            purchase.setPurchaseStringData(stringData);

        } catch (IOException e) {
            throw new ImportException("Ошибка при чтении файла");
        }
        System.out.println("Всего строк" + stringData.size());
    }

    @Override
    public void ExportFile(Purchase purchase, String filePath) {

        Map<String, Double> purchaseDataSortedByDiscout = purchase.getPurchaseDataSortedByDiscout();

        if(purchaseDataSortedByDiscout.isEmpty()|| purchaseDataSortedByDiscout==null){

            throw new ExportException("В покупах еще не было расчет скидок");
        }

        try (BufferedWriter reader = new BufferedWriter(new FileWriter(filePath))) {

            String line = "";

            for(Map.Entry<String, Double> data :purchaseDataSortedByDiscout.entrySet()){
                    line = data.getKey()+" - "+ data.getValue();

                    reader.write(line);
                    reader.newLine();
            }

        } catch (IOException e) {
            throw new ExportException("Ошибка при записи файла");
        }

    }

}
