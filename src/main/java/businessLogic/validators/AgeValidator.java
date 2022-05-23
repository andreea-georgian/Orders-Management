package businessLogic.validators;

import model.Clients;
import presentation.Message;

public class AgeValidator implements Validator<Clients>{
    private final int MIN_AGE = 7;
    Message message;

    /**
     * Metoda valideaza varsta unui client
     * @param t
     * @return
     */
    public boolean validate(Clients t) {
        if (t.getAge() < MIN_AGE) {
            message = new Message("Varsta necorespunzatoare!");
            return false;
        }
        return true;
    }
}
