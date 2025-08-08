package CLASS.SUB;

import CLASS.Product;
import INTERFACE.IProductInflation;


public class Food extends Product implements IProductInflation {
    private final String expirationDate;


    public Food(String name, double price, String expirationDate) {
        super(name, price);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void productFromWhere() {
        System.out.println(" Azərbaycan məhsulu...");

    }

    @Override
    public double calculateInflationRate(double price, double rate) {
        double lastPrice=0;
        lastPrice+=getPrice()+(price*(rate/100));
        return lastPrice;
    }
}
