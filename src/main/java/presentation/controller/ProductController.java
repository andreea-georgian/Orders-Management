package presentation.controller;

import businessLogic.ProductBL;
import presentation.Message;
import presentation.view.AddProductView;
import presentation.view.FirstView;
import presentation.view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController {
    ProductView productView;
    ProductBL productBL;
    Message message;

    /**
     * Constructor cu parametru pentru crearea controller-ului ferestrei cu produse
     * @param productView
     */
    public ProductController(ProductView productView) {
        this.productView = productView;
        productView.addAdaugaListener(new AdaugaListener());
        productView.addEditeazaListener(new EditeazaListener());
        productView.addStergeListener(new StergeListener());
        productView.addBackListener(new BackListener());
        productBL = new ProductBL();
        productBL.populateProductList(productView.productsList, productBL.findAllProducts());
    }

    class AdaugaListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            AddProductView view = new AddProductView();
            AddProductController controller = new AddProductController(view, productView.productsList);
        }
    }

    class EditeazaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String field = (String) productView.dataCB.getSelectedItem();
            int row = productView.productsList.getSelectedRow();
            int column = productView.productsList.getSelectedColumn();
            int id = (int) productView.productsList.getValueAt(row, column);
            Object newValue;
            if (field.equals("Nume")) {
                newValue = productView.textField.getText();
                productBL.updateProduct(id, "productName", newValue);
            }
            else {
                if (field.equals("Cantitate")) {
                    try {
                        newValue = Integer.parseInt(productView.textField.getText());
                        productBL.updateProduct(id, "cantity", newValue);
                    } catch (NumberFormatException nfe) {
                        message = new Message("Cantitate incorecta!");
                    }
                }
                else {
                    try {
                        newValue = Float.parseFloat(productView.textField.getText());
                        productBL.updateProduct(id, "price", newValue);
                    } catch (NumberFormatException nfe) {
                        message = new Message("Pret incorect!");
                    }
                }
            }
            productBL.populateProductList(productView.productsList, productBL.findAllProducts());
        }
    }

    class StergeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = productView.productsList.getSelectedRow();
            int column = productView.productsList.getSelectedColumn();
            int id = (int) productView.productsList.getValueAt(row, column);
            productBL.deleteProduct(id);
            productBL.populateProductList(productView.productsList, productBL.findAllProducts());
        }
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productView.dispose();
            FirstView firstView = new FirstView();
            Controller controller = new Controller(firstView);
        }
    }
}

