package org.example;

import java.util.List;
import java.util.Map;

public interface Discounts {

    public boolean getDiscountsForClientByDate();

    public boolean getTotalWithDiscount(Map<Purchase, Integer> discountMap);
}
