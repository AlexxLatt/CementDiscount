import Processor.FileProcessor;
import Shop.Purchase;

public class Main {
    public static void main(String[] args) {

        Purchase purchase = new Purchase();
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.ImportFile(purchase,"src/main/java/resources/discount_day.txt");


        fileProcessor.parseData(purchase);


        purchase.sortPurchaseByDiscount(50);

        fileProcessor.ExportFile(purchase,"src/main/java/resources/test.txt");


    }
}
