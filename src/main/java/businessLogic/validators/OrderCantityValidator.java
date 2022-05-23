package businessLogic.validators;

import model.Orders;
import presentation.Message;

public class OrderCantityValidator implements Validator<Orders> {
    private final int MIN_CANTITY = 1;
    Message message;

    /**
     * Metoda valideaza cantitatea de produse dintr-o comanda
     * @param t
     * @return
     */
    public boolean validate(Orders t) {
        if (t.getCantity() < MIN_CANTITY) {
            message = new Message("Cantitate prea mica!");
            return false;
        }
        return true;
    }

}
