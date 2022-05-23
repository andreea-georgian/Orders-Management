package businessLogic;

import businessLogic.validators.CantityValidator;
import businessLogic.validators.PriceValidator;
import businessLogic.validators.Validator;
import dataAccess.AbstractDataAccess;
import dataAccess.ProductDataAccess;
import model.Product;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBL {
    private List<Validator<Product>> validators;
    private AbstractDataAccess productDataAccess;

    /**
     * Constructor fara parametru pentru crearea unui obiect
     */
    public ProductBL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new CantityValidator());
        validators.add(new PriceValidator());

        productDataAccess = new ProductDataAccess();
    }

    /**
     * Metoda populeaza un tabel cu datele listei de produse
     * @param table
     * @param productList
     */
    public void populateProductList(JTable table, List<Product> productList) {
        if (productList != null) {
            try {
                productDataAccess.populateTable(table, productList);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda returneaza produsul care are id-ul dat ca parametru
     * @param id
     * @return
     */
    public Product findProductsById (int id) {
        Product product = (Product) productDataAccess.findByID(id);
        if (product == null) {
            throw new NoSuchElementException("Produsul cu id-ul " + id + " nu a fost gasit!");
        }
        return product;
    }

    /**
     * Metoda valideaza si insereaza in baza de date un produs
     * @param product
     * @return
     */
    public int insertProduct (Product product) {
        boolean ok = true;
        for (Validator<Product> v : validators) {
            ok &= v.validate(product);
        }
        if (ok) {
            return productDataAccess.insert(product);
        }
        return -1;
    }

    /**
     * Metoda modifica in baza de date field-ul produsului care are id-ul dat
     * @param id
     * @param fieldName
     * @param newValue
     */
    public void updateProduct(int id, String fieldName, Object newValue) {
        if (id != -1)
            productDataAccess.update(id, fieldName, newValue);
    }

    /**
     * Metoda sterge din baza de date produsul care are id-ul dat ca parametru
     * @param id
     */
    public void deleteProduct(int id) {
        if(id != -1)
            productDataAccess.delete(id);
    }

    /**
     * Metoda returneaza o lista tuturor produselor existente in baza de date
     * @return
     */
    public List<Product> findAllProducts() {
        return productDataAccess.findAll();
    }
}
