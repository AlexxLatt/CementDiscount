package com.example.processor;

import com.example.shop.OrderInvoice;
import com.example.shop.OrderReceipt;
import com.example.shop.Purchase;

public interface ProcessorFiles {

    void exportFile(OrderInvoice orderInvoice, String filePath);


    void importFile(OrderReceipt orderReceipt, String filePath, String typeParser);


}

