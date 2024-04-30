package cash.machine.validator;

import java.util.regex.Pattern;

/**
 * Валидатор входных данных для добавления информации о банкнотах
 */
public class AddCashValidator extends CommonValidator {

    private static final int ADD_NOTE_LENGTH = 4;
    private static final String PLUS_SIGN = "+";

    private static final String VALUE_REGULAR_EXPRESSION = "([15])0{0,3}";
    private static final Pattern VALUE_REGEX_PATTERN = Pattern.compile(VALUE_REGULAR_EXPRESSION);

    @Override
    public boolean validate(String[] inputArray) {

        return inputArray.length == ADD_NOTE_LENGTH
                && inputArray[0].equals(PLUS_SIGN)
                && checkCurrency(inputArray[1])
                && checkRegex(VALUE_REGEX_PATTERN, inputArray[2])
                && checkAnyPositiveInt(inputArray[3]);
    }
}
