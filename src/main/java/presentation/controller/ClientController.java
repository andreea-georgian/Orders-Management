package presentation.controller;

import businessLogic.ClientBL;
import presentation.Message;
import presentation.view.AddClientView;
import presentation.view.ClientView;
import presentation.view.FirstView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController {
    ClientView clientView;
    Message message;
    ClientBL clientBL;

    /**
     * Constructor cu parametru pentru crearea controller-ului ferestrei cu clienti
     * @param clientView
     */
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        clientView.addAdaugaListener(new AdaugaListener());
        clientView.addEditeazaListener(new EditeazaListener());
        clientView.addStergeListener(new StergeListener());
        clientView.addBackListener(new BackListener());
        clientBL = new ClientBL();
        clientBL.populateClientList(clientView.clientsList, clientBL.findAllClients());
    }

    class AdaugaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AddClientView view = new AddClientView();
            AddClientController controller = new AddClientController(view, clientView.clientsList);
        }
    }

    class EditeazaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String field = (String) clientView.dataCB.getSelectedItem();
            int row = clientView.clientsList.getSelectedRow();
            int column = clientView.clientsList.getSelectedColumn();
            int id = (int) clientView.clientsList.getValueAt(row, column);
            Object newValue;
            if (field.equals("Nume")) {
                newValue = clientView.textField.getText();
                clientBL.updateClient(id, "clientName", newValue);
            } else if (field.equals("Adresa")) {
                newValue = clientView.textField.getText();
                clientBL.updateClient(id, "adress", newValue);
            } else if (field.equals("Email")) {
                newValue = clientView.textField.getText();
                clientBL.updateClient(id, "email", newValue);
            } else {
                try {
                    newValue = Integer.parseInt(clientView.textField.getText());
                    clientBL.updateClient(id, "age", newValue);
                } catch (NumberFormatException nfe) {
                    message = new Message("Varsta incorecta!");
                }
            }
            clientBL.populateClientList(clientView.clientsList, clientBL.findAllClients());
        }
    }

    class StergeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = clientView.clientsList.getSelectedRow();
            int column = clientView.clientsList.getSelectedColumn();
            int id = (int) clientView.clientsList.getValueAt(row, column);
            clientBL.deleteClient(id);
            clientBL.populateClientList(clientView.clientsList, clientBL.findAllClients());
        }
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clientView.dispose();
            FirstView firstView = new FirstView();
            Controller controller = new Controller(firstView);
        }
    }
}
