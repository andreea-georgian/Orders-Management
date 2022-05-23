package businessLogic.validators;

import model.Product;
import presentation.Message;

public class PriceValidator implements Validator<Product> {
    private final float MIN_PRICE = 0;
    Message message;

    /**
     * Metoda valideaza pretul unui produs
     * @param t
     * @return
     */
    public boolean validate(Product t) {
        if (t.getPrice() < MIN_PRICE) {
            message = new Message("Pret gresit!");
            return false;
        }
        return true;
    }
}
