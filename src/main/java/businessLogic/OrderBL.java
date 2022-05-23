package businessLogic;

import businessLogic.validators.OrderCantityValidator;
import businessLogic.validators.Validator;
import dataAccess.AbstractDataAccess;
import dataAccess.OrderDataAccess;
import model.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderBL {
    private List<Validator<Orders>> validators;
    private AbstractDataAccess orderDataAccess;

    /**
     * Contructor fara parametru pentru crearea unui obiect
     */
    public OrderBL() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new OrderCantityValidator());

        orderDataAccess = new OrderDataAccess();
    }

    /**
     * Metoda valideaza si insereaza in baza de date o comanda
     * @param order
     * @return
     */
    public int insertOrder(Orders order) {
        boolean ok = true;
        for (Validator<Orders> v : validators) {
            ok &= v.validate(order);
        }
        if (ok) {
            return orderDataAccess.insert(order);
        }
        return -1;
    }
}
