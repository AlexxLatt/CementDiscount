package Interfaces;

import Shop.Purchase;

public interface FileProcessor {

    public void ExportFile(Purchase purchase,String filePath);


    public void ImportFile(Purchase purchase,String filePath);



}
