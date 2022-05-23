package presentation.controller;

import businessLogic.ClientBL;
import businessLogic.OrderBL;
import businessLogic.ProductBL;
import model.Orders;
import model.Product;
import presentation.Message;
import presentation.view.FirstView;
import presentation.view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class OrderController {
    OrderView orderView;
    OrderBL orderBL;
    ProductBL productBL;
    ClientBL clientBL;
    Message message;

    /**
     * Constructor cu parametru pentru crearea controller-ului ferestrei pentru comanda
     * @param orderView
     */
    public OrderController(OrderView orderView) {
        this.orderView = orderView;
        this.orderView.addAdaugaListener(new AdaugaListener());
        this.orderView.addBackListener(new BackListener());
        orderBL = new OrderBL();
        productBL = new ProductBL();
        clientBL = new ClientBL();
        this.orderView.addElementsToClientTable(clientBL.findAllClients(), new String[]{"ID client", "Nume client"});
        this.orderView.addElementsToProductTable(productBL.findAllProducts(), new String[]{"Nume", "Cantitate", "Pret"});
    }

    public void generateBill(Orders o) throws IOException {
        FileWriter file = new FileWriter("Bill" + o.getID() + ".txt");
        file.append("Comanda numarul " + o.getID() + "\n");
        file.append("Client " + o.getClientID() + "\n\n");
        file.append("Produs comandat: " + productBL.findProductsById(o.getProductID()).getProductName() + "\n");
        file.append("Pret produs :" + productBL.findProductsById(o.getProductID()).getPrice() + " lei\n");
        file.append("Cantitate: " + o.getCantity() + "\n");
        file.append("Pretul total al comenzii: " + o.getPrice() + "\n\n");
        file.append("Multumim pentru incredere! :)");
        file.close();
    }

    public void decrementStock(Orders o) {
        int newCantity = productBL.findProductsById(o.getProductID()).getCantity() - o.getCantity();
        productBL.updateProduct(o.getProductID(), "cantity", newCantity);
    }

    class AdaugaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = orderView.clientsList.getSelectedRow();
            int column = orderView.clientsList.getSelectedColumn();
            int clientId = (int) orderView.clientsList.getValueAt(row, column);
            row = orderView.productsList.getSelectedRow();
            column = orderView.productsList.getSelectedColumn();
            String productName = (String) orderView.productsList.getValueAt(row, column);
            int productId = -1;
            for (Product p : productBL.findAllProducts()) {
                if (p.getProductName().equals(productName)) {
                    productId = p.getID();
                    break;
                }
            }
            int cantity = Integer.parseInt(orderView.textField.getText());
            float price = productBL.findProductsById(productId).getPrice() * cantity;
            if (cantity <= productBL.findProductsById(productId).getCantity()) {
                Orders o = new Orders(clientId, productId, cantity, price);
                int ok = orderBL.insertOrder(o);
                if (ok != -1) {
                    message = new Message("Comanda a fost creata cu succes!");
                    decrementStock(o);
                    orderView.addElementsToProductTable(productBL.findAllProducts(), new String[]{"Nume", "Cantitate", "Pret"});
                    try {
                        o.setID(ok);
                        generateBill(o);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else
                message = new Message("Stoc insuficient!");
        }
    }

    class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            orderView.dispose();
            FirstView firstView = new FirstView();
            Controller controller = new Controller(firstView);
        }
    }
}
