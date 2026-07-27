package com.example.processor;

import com.example.shop.OrderInvoice;
import com.example.shop.Order;
import com.example.shop.OrderReceipts;

public interface ProcessorFiles {

    void exportFile(OrderInvoice orderInvoice, String filePath);


    void importFile(OrderReceipts orderReceipts, String filePath, String typeParser);


}

