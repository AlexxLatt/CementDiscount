package org.example;

import java.time.LocalDateTime;
import java.util.Map;

public class Purchase {


    private Client client;
    private Product product;
    private LocalDateTime purchaseDate;
    private int quantityBuy;
    private int totalBuy;


    public Purchase(Client client, Product product, int quantityBuy, LocalDateTime purchaseDate) {
        this.client = client;
        this.product = product;
        this.purchaseDate = purchaseDate;
        this.quantityBuy = quantityBuy;
        this.totalBuy = quantityBuy * product.getPrice();
    }

    // Геттеры и сеттеры
    public Client getClient() {
        return client;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setTotalBuy(int totalBuy) {
        this.totalBuy = totalBuy;
    }

    public int getTotalBuy() {
        return totalBuy;
    }

    public void applyDiscount(int discountPercent) {

        if (discountPercent == 0) {
            return;
        }
        setTotalBuy((int) (getTotalBuy() * (1 - discountPercent / 100.0)));


    }


    @Override
    public String toString() {
        return "Purchase{" +
                "client=" + client.getCompanyName() +
                ", product=" + product.getName() +
                ", quantity=" + quantityBuy +
                ", date=" + purchaseDate +
                ", totalBuy=" + totalBuy +
                '}';
    }
}