package org.example;

import com.example.adapters.DataParserAdapter;
import com.example.processor.FileParser;
import com.example.processor.FileProcessor;
import com.example.shop.OrderInvoice;
import com.example.shop.OrderReceipt;
import com.example.shop.Purchase;

public class Main {
    public static void main(String[] args) {

        Purchase purchase = new Purchase();
        FileProcessor fileProcessor = new FileProcessor();


        OrderReceipt orderReceipt = new OrderReceipt();
        OrderInvoice orderInvoice = new OrderInvoice();


        fileProcessor.importFile(orderReceipt, "src/main/java/resources/discount_day.txt", "|");

        purchase.sortPurchaseByDiscount(orderInvoice, orderReceipt, 50);


        fileProcessor.exportFile(orderInvoice, "src/main/java/resources/test1.txt");


        fileProcessor.importFile( orderReceipt, "src/main/java/resources/discount_day_without_ext.txt", "#");

        purchase.sortPurchaseByDiscount(orderInvoice, orderReceipt, 50);


        fileProcessor.exportFile(orderInvoice, "src/main/java/resources/test2.txt");


    }
}
