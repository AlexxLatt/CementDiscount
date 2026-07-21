package org.example;

import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop();

        ExportFile exporter = new FileExporter();

        FileImporter fileImporter = new FileImporter();

        List<String> importedFile =  fileImporter.ImportFile();


        Product product1 = new Product(1000, 10, "Цемент");

        shop.addProdutc(product1);

        Client client1 = new Client("РЖД");

        Purchase purchase = new Purchase(client1, product1, 5, LocalDateTime.now());

        Purchase purchase2 = new Purchase(client1, product1, 5, LocalDateTime.of(2023, 7, 19, 10, 30));

        //shop.buyProduct(purchase);
        //shop.buyProduct(purchase2);


        //shop.getDiscountsForClientByDate();


        //exporter.sortAndExportFile("discounts.csv", "csv", shop.getDiscountPurchaseData() );

        shop.importData(importedFile,"|");
        System.out.println(shop.toString());





    }
}
