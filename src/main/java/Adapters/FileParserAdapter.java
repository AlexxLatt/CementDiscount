package Adapters;

import Exceptions.FileParserExeption;
import Interfaces.DataParser;

import Shop.Purchase;

import java.time.LocalDateTime;
import java.util.*;

public class FileParserAdapter  implements DataParser {


    public void parseData(Purchase purchase) {

        List<String> stringData = purchase.getPurchaseStringData();
        List<String[]> parsedData = new ArrayList();

        Map<LocalDateTime, Map<String, Double>> finalData = new TreeMap<>();

        LocalDateTime purchaseDate;
        String companyName;
        double purchaseAmount;


        int index = 0;
        if (stringData.isEmpty() || stringData == null) {
            throw new FileParserExeption("Данные не импортирвались");


        } else {
            for (String data : stringData) {

                parsedData.add(data.split("#"));
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
}