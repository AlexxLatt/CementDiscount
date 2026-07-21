package org.example;

import java.util.Map;

public interface ExportFile {


    boolean sortAndExportFile(String filePath, String type, Map<Purchase, Integer> discountPurchaseData);
}