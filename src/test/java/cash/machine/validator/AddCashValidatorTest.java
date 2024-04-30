package cash.machine.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCashValidatorTest {

    private final AddCashValidator addCashValidator = new AddCashValidator();

    @Test
    public void validateSuccess() {
        String[] array = new String[]{"+", "USD", "1000", "2"};

        boolean result = addCashValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateFailIfLengthLess() {
        String[] array = new String[]{"+", "USD", "1000"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfLengthMore() {
        String[] array = new String[]{"+", "USD", "1000", "2", "4"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfNotPlus() {
        String[] array = new String[]{"=", "QWE", "1000", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfCurrencyLonger() {
        String[] array = new String[]{"+", "QWER", "1000", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfCurrencyNotUpperCase() {
        String[] array = new String[]{"+", "USd", "1000", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateSuccessIfValueMin() {
        String[] array = new String[]{"+", "USD", "1", "2"};

        boolean result = addCashValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateSuccessIfValueMax() {
        String[] array = new String[]{"+", "USD", "5000", "2"};

        boolean result = addCashValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateFailIfValueZero() {
        String[] array = new String[]{"+", "USD", "0", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfValueNegative() {
        String[] array = new String[]{"+", "USD", "-10", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfValueMore() {
        String[] array = new String[]{"+", "USD", "10000", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfValueNotDigit() {
        String[] array = new String[]{"+", "USD", "1r00", "2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateSuccessIfNumberMin() {
        String[] array = new String[]{"+", "USD", "10", "1"};

        boolean result = addCashValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateFailIfNumberZero() {
        String[] array = new String[]{"+", "USD", "10", "0"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfNumberNegative() {
        String[] array = new String[]{"+", "USD", "10", "-2"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfNumberNotDigit() {
        String[] array = new String[]{"+", "USD", "100", "2f3"};

        boolean result = addCashValidator.validate(array);
        assertFalse(result);
    }
}
