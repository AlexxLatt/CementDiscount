package Shop;

import Interfaces.PurchaseManager;

import java.time.LocalDateTime;
import java.util.*;

public class Purchase implements PurchaseManager {

    private Map<LocalDateTime, Map<String, Double>> purchaseData = new TreeMap<>();

    private List<String> purchaseStringData = new ArrayList<>();

    Map<String, Double> purchaseDataSortedByDiscout = new TreeMap<>();

    Map<String, LocalDateTime> lastDateOfPurchase= new TreeMap<>();


    @Override
    public void sortPurchaseByDiscount(double discount) {

        double currentDiscount = discount;

        for (Map.Entry<LocalDateTime, Map<String, Double>> data : purchaseData.entrySet()) {
            LocalDateTime purchaseDate = data.getKey();
            Map<String, Double> innerMap = data.getValue();

            for (Map.Entry<String, Double> company : innerMap.entrySet()) {
                String companyName = company.getKey();
                Double price = company.getValue();

                double discountedPrice = price - (price / 100 * currentDiscount);


                purchaseDataSortedByDiscout.merge(companyName, discountedPrice, Double::sum);

                LocalDateTime existingDate = lastDateOfPurchase.get(companyName);
                if (existingDate == null || purchaseDate.isAfter(existingDate)) {
                    lastDateOfPurchase.put(companyName, purchaseDate);
                }

                currentDiscount -= 5;
                if (currentDiscount <= 0) {
                    currentDiscount = 0;
                }
            }
        }


        for (Map.Entry<String, Double> entry : purchaseDataSortedByDiscout.entrySet()) {
            System.out.println(entry.getKey() + " | последняя покупка: " +
                    lastDateOfPurchase.get(entry.getKey()) + " | сумма: " +
                    entry.getValue());
        }


    }

    private LocalDateTime dateOfPurchase;


    private String rootFile;


    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getRootFile() {
        return rootFile;
    }

    public void setRootFile(String rootFile) {
        this.rootFile = rootFile;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public List<String> getPurchaseStringData() {
        return purchaseStringData;
    }

    public void setPurchaseStringData(List<String> purchaseStringData) {
        this.purchaseStringData = purchaseStringData;
    }


    public Map<LocalDateTime, Map<String, Double>> getPurchaseData() {
        return purchaseData;
    }

    public void setPurchaseData(Map<LocalDateTime, Map<String, Double>> purchaseData) {
        this.purchaseData = purchaseData;
    }
    public Map<String, LocalDateTime> getLastDateOfPurchase() {
        return lastDateOfPurchase;
    }

    public void setLastDateOfPurchase(Map<String, LocalDateTime> lastDateOfPurchase) {
        this.lastDateOfPurchase = lastDateOfPurchase;
    }

    public Map<String, Double> getPurchaseDataSortedByDiscout() {
        return purchaseDataSortedByDiscout;
    }

    public void setPurchaseDataSortedByDiscout(Map<String, Double> purchaseDataSortedByDiscout) {
        this.purchaseDataSortedByDiscout = purchaseDataSortedByDiscout;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseData=" + purchaseData +
                ", purchaseStringData=" + purchaseStringData +
                ", purchaseDataSortedByDiscout=" + purchaseDataSortedByDiscout +
                ", lastDateOfPurchase=" + lastDateOfPurchase +
                ", dateOfPurchase=" + dateOfPurchase +
                ", rootFile='" + rootFile + '\'' +
                '}';
    }
}
