package model;

public class Orders {
    private int ID;
    private int clientID;
    private int productID;
    private int cantity;
    private float price;

    /**
     * Constructor fara parametrii pentru crearea unei comenzi
     */
    public Orders() {
    }

    /**
     * Constructor cu parametrii pentru crearea unei comenzi
     * @param clientID
     * @param productID
     * @param cantity
     * @param price
     */
    public Orders(int clientID, int productID, int cantity, float price) {
        super();
        this.clientID = clientID;
        this.productID = productID;
        this.cantity = cantity;
        this.price = price;
    }

    /**
     * Metoda returneaza id-ul comenzii
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Metoda seteaza id-ul comenzii la o valoare data
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Metoda returneaza id-ul clientului care a facut comanda
     * @return
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Metoda seteaza id-ul clientului care a facut comanda la o valoare data
     * @param clientID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Metoda returneaza id-ul produsului care a fost comandat
     * @return
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Metoda seteaza id-ul produsului care a fost comandat la o valoare data
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Metoda returneaza cantitatea comandata
     * @return
     */
    public int getCantity() {
        return cantity;
    }

    /**
     * Metoda seteaza cantitatea comandata la o valoare data
     * @param cantity
     */
    public void setCantity(int cantity) {
        this.cantity = cantity;
    }

    /**
     * Metoda returneaza pretul comenzii
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * Metoda seteaza pretul comenzii la o valoare data
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Metoda returneaza reprezentarea sub forma de String a comenzii
     * @return
     */
    public String toString() {
        return "Orders{" +
                "ID=" + ID +
                ", clientID=" + clientID +
                ", productID=" + productID +
                ", cantity=" + cantity +
                ", price=" + price +
                '}';
    }
}
