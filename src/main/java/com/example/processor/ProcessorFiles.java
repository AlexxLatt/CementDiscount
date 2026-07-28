package com.example.processor;

import com.example.shop.Сhecks;
import com.example.shop.Order;

import java.util.List;

public interface ProcessorFiles {

    List<Order> importFile(String filePath);

}

