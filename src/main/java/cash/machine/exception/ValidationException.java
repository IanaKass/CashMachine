package cash.machine.exception;

/**
 * Исключение валидации
 */
public class ValidationException extends Exception {

    public ValidationException() {
        super("Validation error");
    }

}
