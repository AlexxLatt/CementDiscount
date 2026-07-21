package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileExporter implements ExportFile {

    @Override
    public boolean sortAndExportFile(String filePath, String type, Map<Purchase, Integer> discountPurchaseData) {
        if (discountPurchaseData.isEmpty() || discountPurchaseData == null) {
            System.out.println("Покупок в магазине нет - эксопрт остановлен");
            return false;
        }


        Map<String, Double> companyTotals = new HashMap<>();
        Map<String, LocalDateTime> companyLastDate = new HashMap<>();

        for (Map.Entry<Purchase, Integer> entry : discountPurchaseData.entrySet()) {

            Purchase p = entry.getKey();
            String company = p.getClient().getCompanyName();
            double total = p.getTotalBuy();
            LocalDateTime date = p.getPurchaseDate();


            companyTotals.put(company, companyTotals.getOrDefault(company, 0.0) + total);


            if (!companyLastDate.containsKey(company) || companyLastDate.get(company).isBefore(date)) {
                companyLastDate.put(company, date);
            }
        }


        List<Map.Entry<String, LocalDateTime>> sortedCompanies = companyLastDate.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


            writer.newLine();


            for (Map.Entry<String, LocalDateTime> entry : sortedCompanies) {
                String company = entry.getKey();
                LocalDateTime lastDate = entry.getValue();
                double total = companyTotals.get(company);
                LocalDateTime dateWithoutNanos = lastDate.withNano(0);

                String formattedTotal = String.format("%.0f", total);
                String formattedDate = dateWithoutNanos.format(formatter);


                writer.write(formattedDate + "|" + company + "|" + formattedTotal);
                writer.newLine();
            }

            System.out.println("Файл успешно создан: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
            return false;
        }
    }

}
