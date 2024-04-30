package cash.machine.validator;

/**
 * Валидатор входных данных для печати информации о всех наличных
 */
public class PrintCashSummaryValidator extends CommonValidator {

    private static final int PRINT_CASH_LENGTH = 1;
    private static final String QUESTION_SIGN = "?";

    @Override
    public boolean validate(String[] inputArray) {
        return inputArray.length == PRINT_CASH_LENGTH
                && inputArray[0].equals(QUESTION_SIGN);
    }
}
