package org.example;

import java.util.List;

public interface IShop {

    public boolean buyProduct(Purchase purchase);
    public boolean importData(List<String> importedData, String separator );
}
