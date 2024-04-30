package cash.machine.validator;

/**
 * Валидатор входных данных для выдачи наличных
 */
public class CashOutValidator extends CommonValidator {

    private static final int GET_CASH_LENGTH = 3;
    private static final String MINUS_SIGN = "-";

    @Override
    public boolean validate(String[] inputArray) {

        return inputArray.length == GET_CASH_LENGTH
                && inputArray[0].equals(MINUS_SIGN)
                && checkCurrency(inputArray[1])
                && checkAnyPositiveInt(inputArray[2]);
    }
}
