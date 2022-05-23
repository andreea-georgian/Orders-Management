package presentation.controller;

import businessLogic.ProductBL;
import model.Product;
import presentation.Message;
import presentation.view.AddProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddProductController {
    AddProductView view;
    ProductBL productBL;
    JTable table;
    Message message;

    /**
     * Constructor cu parametrii pentru crearea controller-ului ferestrei de adaugare a unui produs nou
     * @param view
     * @param table
     */
    public AddProductController(AddProductView view, JTable table) {
        this.view = view;
        this.table = table;
        this.view.addAdaugaListener(new adaugaListener());
        productBL = new ProductBL();
    }

    class adaugaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nume = view.numeTF.getText();
            int cantity = Integer.parseInt(view.cantitateTF.getText());
            float price = Float.parseFloat(view.pretTF.getText());
            Product product = new Product(nume, cantity, price);
            int ok = productBL.insertProduct(product);
            if (ok != -1) {
                message = new Message("Produs adaugat cu succes!");
                productBL.populateProductList(table, productBL.findAllProducts());
            }
        }
    }
}
