package CLASS;

import java.util.Random;

public abstract class Product {
    protected String name;
    protected double price;
    protected String ID;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;


    }
    public String getName() {
        return name;
    }

    //public void setName(String name) {
      //  this.name = name;
   // }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID() {
        Random rnd=new Random();
        this.ID=String.valueOf(rnd.nextInt(10000,99999));
    }
    //public void setID(int id) {
        //Random rnd=new Random();
   //     this.ID=String.valueOf(id);
   // }

    public abstract void productFromWhere();//bu metot legv edilib hansi olkeden idxal edildiyi giseterilsin
}
