package model;

public class Product {
    private int ID;
    private String productName;
    private int cantity;
    private float price;

    /**
     * Constructor fara parametrii pentru crearea unui produs
     */
    public Product() {
    }

    /**
     * Constructor cu parametrii pentru crearea unui produs
     * @param productName
     * @param cantity
     * @param price
     */
    public Product(String productName, int cantity, float price) {
        super();
        this.productName = productName;
        this.cantity = cantity;
        this.price = price;
    }

    /**
     * Metoda returneaza id-ul produsului
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Metoda seteaza id-ul produsului la o valoare data
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Metoda returneaza numele produsului
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Metoda seteaza numele produsului la o valoare data
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Metoda returneaza cantitatea produsului
     * @return
     */
    public int getCantity() {
        return cantity;
    }

    /**
     * Metoda seteaza cantitatea produsului la o valoare data
     * @param cantity
     */
    public void setCantity(int cantity) {
        this.cantity = cantity;
    }

    /**
     * Metoda returneaza pretul produsului
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * Metoda seteaza pretul produsului la o valoare data
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Metoda returneaza reprezentarea sub forma de String a produsului
     * @return
     */
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", productName='" + productName + '\'' +
                ", cantity=" + cantity +
                ", price=" + price +
                '}';
    }
}
