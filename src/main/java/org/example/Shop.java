package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Shop implements IShop, Discounts {

    private int discount = 50;
    private Map<Purchase, Integer> discountPurchaseData = new HashMap<>();
    private List<Purchase> purchasesData = new ArrayList<>();
    private Set<Product> productData = new HashSet<>();


    public boolean addProdutc(Product product) {

        System.out.println("Вы добавили в магазин товар: " + product.getName());
        productData.add(product);
        return true;


    }

    @Override
    public boolean getTotalWithDiscount(Map<Purchase, Integer> discountMap) {

        return false;

    }

    @Override
    public boolean getDiscountsForClientByDate() {
        if (productData.isEmpty()) {
            System.out.println("Нет покупателей для просчета скидки");
            return false;
        } else {


            purchasesData.sort(Comparator.comparing(purchase -> purchase.getPurchaseDate()));
            discountPurchaseData.clear();


            for (Purchase p : purchasesData) {

                p.applyDiscount(getDiscount());
                discountPurchaseData.put(p, getDiscount());
                setDiscount(getDiscount() - 5);
                if (discount == 0) {

                    discountPurchaseData.put(p, 0);

                }
            }


            System.out.println("Пересчет скидки прошел успешно!");
            return true;
        }
    }


    @Override
    public boolean buyProduct(Purchase purchase) {
        if (!productData.contains(purchase.getProduct())) {
            System.out.println("Такого продукта нет");
            return false;
        } else {
            System.out.println("Клиент " + purchase.getClient().getCompanyName() + " купил " + purchase.getQuantityBuy() + "т - " + purchase.getProduct().getName() + " на сумму " + purchase.getTotalBuy());
            purchasesData.add(purchase);

            return true;
        }


    }

    public Map<Purchase, Integer> getDiscountPurchaseData() {
        return discountPurchaseData;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Shop{" + "discountMap=" + discountPurchaseData +

                '}';
    }

    @Override
    public boolean importData(List<String> importedData, String separator) {
        Map<Integer, String[]> sortedImportedData = new LinkedHashMap<>();
        String escapedSeparator = Pattern.quote(separator);
        Product product = new Product(1000, 10, "Цемент");
        addProdutc(product);

        if (importedData.isEmpty() || importedData == null) {
            System.out.println("В дате нет данных");
            return false;

        } else {
            int id = 1;
            for (String data : importedData) {
                sortedImportedData.put(id, data.split(escapedSeparator));


                LocalDateTime date = LocalDateTime.parse(sortedImportedData.get(id)[0]);
                String companyName = sortedImportedData.get(id)[1];
                int totalBuy = Integer.parseInt(sortedImportedData.get(id)[2]);
                Client client = new Client(companyName);


                Purchase purchase = new Purchase(client, product, 1, date);

                buyProduct(purchase);
            }

            System.out.println("Данные импортирвались в магазин:");

            return true;


        }
    }
}
