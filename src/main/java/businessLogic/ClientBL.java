package businessLogic;

import businessLogic.validators.AgeValidator;
import businessLogic.validators.EmailValidator;
import businessLogic.validators.Validator;
import dataAccess.AbstractDataAccess;
import dataAccess.ClientDataAccess;
import model.Clients;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBL {
    private List<Validator<Clients>> validators;
    private AbstractDataAccess clientDataAccess;

    /**
     * Condtructor fara parametru pentru crearea obiectului
     */
    public ClientBL() {
        validators = new ArrayList<Validator<Clients>>();
        validators.add(new AgeValidator());
        validators.add(new EmailValidator());

        clientDataAccess = new ClientDataAccess();
    }

    /**
     * Metoda care populeaza un tabel cu datele listei de clienti
     * @param table
     * @param clientsList
     */
    public void populateClientList(JTable table, List<Clients> clientsList) {
        if (clientsList != null) {
            try {
                clientDataAccess.populateTable(table, clientsList);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda returneaza clientul care are id-ul dat ca parametru
     * @param id
     * @return
     */
    public Clients findClientById(int id) {
        Clients client = (Clients) clientDataAccess.findByID(id);
        if (client == null) {
            throw new NoSuchElementException("Clientul cu id-ul " + id + " nu a fost gasit!");
        }
        return client;
    }

    /**
     * Metoda valideaza si insereaza un client in baza de date
     * @param client
     * @return
     */
    public int insertClient(Clients client) {
        boolean ok = true;
        for (Validator<Clients> v : validators) {
            ok &= v.validate(client);
        }
        if (ok) {
            return clientDataAccess.insert(client);
        }
        return -1;
    }

    /**
     * Metoda modifica in baza de date field-ul clientului cu id-ul dat
     * @param id
     * @param fieldName
     * @param newValue
     */
    public void updateClient(int id, String fieldName, Object newValue) {
        if (id != -1)
            clientDataAccess.update(id, fieldName, newValue);
    }

    /**
     * Metoda sterge din baza de date clientul care are id-ul dat ca parametru
     * @param id
     */
    public void deleteClient(int id) {
        if (id != -1)
            clientDataAccess.delete(id);
    }

    /**
     * Metoda returneaza lista tuturor clientilor existenti in baza de date
     * @return
     */
    public List<Clients> findAllClients() {
        return clientDataAccess.findAll();
    }
}
