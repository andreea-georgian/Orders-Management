package businessLogic.validators;

import model.Product;
import presentation.Message;

public class CantityValidator implements Validator<Product> {
    private final int MIN_CANTITY = 10;
    Message message;

    /**
     * Metoda valideaza cantitatea unui produs
     * @param t
     * @return
     */
    public boolean validate(Product t) {
        if (t.getCantity() < MIN_CANTITY) {
            message = new Message("Cantitate prea mica!");
            return false;
        }
        return true;
    }

}
