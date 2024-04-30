package cash.machine.exception;

/**
 * Исключение при невозможности выдать запрошенную сумму
 */
public class UnavailableCashOutException extends Exception {

    public UnavailableCashOutException() {
        super("It is impossible to cash out the required number");
    }
}
