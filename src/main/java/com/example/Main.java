package com.example;

import com.example.processor.FileProcessor;
import com.example.shop.OrderInvoice;
import com.example.shop.Order;
import com.example.shop.OrderReceipts;
import com.example.shop.Purchase;

public class Main {
    public static void main(String[] args) {

        Purchase purchase = new Purchase();
        FileProcessor fileProcessor = new FileProcessor();


        OrderReceipts orderReceipts1 = new OrderReceipts();
        OrderInvoice orderInvoice1 = new OrderInvoice();


        OrderReceipts orderReceipts2 = new OrderReceipts();
        OrderInvoice orderInvoice2 = new OrderInvoice();


        fileProcessor.importFile(orderReceipts1, "src/main/resources/discount_day.txt", "|");
        purchase.sortPurchaseByDiscount(orderReceipts1, orderInvoice1, 50);
        fileProcessor.exportFile(orderInvoice1, "src/main/resources/test1.txt");


        fileProcessor.importFile(orderReceipts2, "src/main/resources/discount_day_without_ext", "#");
        purchase.sortPurchaseByDiscount(orderReceipts2, orderInvoice2, 50);
        fileProcessor.exportFile(orderInvoice2, "src/main/resources/test2.txt");


    }
}
