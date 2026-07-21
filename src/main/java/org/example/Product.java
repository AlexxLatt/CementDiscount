package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {

    private String name ;
    private int price;
    private int quantity;


    public Product(int price,int quantity, String name) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;


    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
