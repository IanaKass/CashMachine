package cash.machine.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Общий класс для валидации
 */
public abstract class CommonValidator implements Validator {

    private static final String CURRENCY_REGULAR_EXPRESSION = "[A-Z]{3}";
    private static final Pattern CURRENCY_REGEX_PATTERN = Pattern.compile(CURRENCY_REGULAR_EXPRESSION);

    public abstract boolean validate(String[] inputArray);

    protected boolean checkRegex(Pattern pattern, String value) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    protected boolean checkCurrency(String value) {
        return checkRegex(CURRENCY_REGEX_PATTERN, value);
    }

    protected boolean checkAnyPositiveInt(String value) {
        try {
            return Integer.parseInt(value) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
