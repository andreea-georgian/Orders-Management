package presentation.controller;

import businessLogic.ClientBL;
import model.Clients;
import presentation.Message;
import presentation.view.AddClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientController {
    AddClientView view;
    ClientBL clientBL;
    JTable table;
    Message message;

    /**
     * Contructor cu parametrii pentru crearea controller-ului ferestrei de adaugare a unui client nou
     * @param view
     * @param table
     */
    public AddClientController(AddClientView view, JTable table) {
        this.view = view;
        this.view.addAdaugaListener(new AdaugaListener());
        this.table = table;
        clientBL = new ClientBL();
    }

    class AdaugaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nume = view.numeTF.getText();
            String adress = view.adresaTF.getText();
            String email = view.emailTF.getText();
            int age = Integer.parseInt(view.varstaTF.getText());
            Clients client = new Clients(nume, adress, email, age);
            int ok = clientBL.insertClient(client);
            if (ok != -1) {
                message = new Message("Client adaugat cu succes!");
                clientBL.populateClientList(table, clientBL.findAllClients());
            }
        }
    }
}
