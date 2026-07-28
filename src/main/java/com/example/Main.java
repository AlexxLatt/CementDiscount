package com.example;

import com.example.processor.ExportManager;
import com.example.processor.ImportManager;
import com.example.shop.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        ImportManager importManager = new ImportManager();
        ExportManager exportManager = new ExportManager();
        Purchase purchase = new Purchase();

        List<Order> orders = importManager.processImport("src/main/resources/discount_day.txt");

        List<Order> orders2 = importManager.processImport("src/main/resources/discount_day_without_ext");

        Сhecks сhecks = purchase.sortPurchaseByDiscount(orders, 50);
        Сhecks сhecks2 = purchase.sortPurchaseByDiscount(orders2, 50);

        exportManager.processExport(сhecks, "src/main/resources/test3.txt");
        exportManager.processExport(сhecks2, "src/main/resources/test4.txt");
    }
}
