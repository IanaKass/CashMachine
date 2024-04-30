package cash.machine.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CashOutValidatorTest {

    private final CashOutValidator cashOutValidator = new CashOutValidator();

    @Test
    public void validateSuccess() {
        String[] array = new String[]{"-", "USD", "1510"};

        boolean result = cashOutValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateFailIfLengthLess() {
        String[] array = new String[]{"-", "USD"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfLengthMore() {
        String[] array = new String[]{"-", "USD", "1000", "300"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfNotMinus() {
        String[] array = new String[]{"=", "QWE", "1000"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfCurrencyLonger() {
        String[] array = new String[]{"-", "QWER", "1000"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfCurrencyNotUpperCase() {
        String[] array = new String[]{"-", "USd", "1000"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateSuccessIfAmountMin() {
        String[] array = new String[]{"-", "USD", "1"};

        boolean result = cashOutValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateFailIfAmountZero() {
        String[] array = new String[]{"-", "USD", "0"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfAmountNegative() {
        String[] array = new String[]{"-", "USD", "-10"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfAmountNotDigit() {
        String[] array = new String[]{"-", "USD", "1r00"};

        boolean result = cashOutValidator.validate(array);
        assertFalse(result);
    }

}
