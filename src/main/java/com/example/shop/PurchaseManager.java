package com.example.shop;

import java.util.List;

public interface PurchaseManager {

    void sortPurchaseByDiscount(OrderReceipts orderReceipts, OrderInvoice orderInvoice, double discount);

}
