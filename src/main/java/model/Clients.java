package model;

public class Clients {
    private int ID;
    private String clientName;
    private String adress;
    private String email;
    private int age;

    /**
     * Constructor fara parametrii pentru crearea unui client
     */
    public Clients() {
    }

    /**
     * Constructor cu parametrii pentru crearea unui client
     * @param clientName
     * @param adress
     * @param email
     * @param age
     */
    public Clients(String clientName, String adress, String email, int age) {
        super();
        this.clientName = clientName;
        this.adress = adress;
        this.email = email;
        this.age = age;
    }

    /**
     * Metoda returneaza id-ul clientului
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Metoda seteaza id-ul la o valoare data
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Metoda returneaza numele clientului
     * @return
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Metoda seteaza numele clientului la o valoare data
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Metoda returneaza adresa clientului
     * @return
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Metoda seteaza adresa clientului la o valoare data
     * @param adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Metoda returneaza email-ul clientului
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metoda seteaza email-ul clientului la o valoare data
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda returneaza varsta clientului
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * Metoda seteaza varsta clientului la o valoare data
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Metoda returneaza reprezentarea sub forma de String a clientului
     * @return
     */
    public String toString() {
        return "Clients{" +
                "ID=" + ID +
                ", clientName='" + clientName + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
