package presentation.controller;

import presentation.view.ClientView;
import presentation.view.FirstView;
import presentation.view.OrderView;
import presentation.view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    FirstView firstView;

    /**
     * Constructor cu parametru pentru crearea controller-ului
     * @param firstView
     */
    public Controller (FirstView firstView) {
        this.firstView = firstView;
        firstView.addClientListener(new ClientListener());
        firstView.addProdusListener(new ProdusListener());
        firstView.addComandaListener(new ComandaListener());
    }

    class ClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            firstView.dispose();
            ClientView clientView = new ClientView();
            ClientController clientController = new ClientController(clientView);
        }
    }

    class ProdusListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            firstView.dispose();
            ProductView productView = new ProductView();
            ProductController productController = new ProductController(productView);
        }
    }

    class ComandaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            firstView.dispose();
            OrderView orderView = new OrderView();
            OrderController orderController = new OrderController(orderView);
        }
    }
}
