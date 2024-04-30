package cash.machine.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrintCashSummaryValidatorTest {

    private final PrintCashSummaryValidator printCashSummaryValidator
            = new PrintCashSummaryValidator();

    @Test
    public void validateSuccess() {
        String[] array = new String[]{"?"};

        boolean result = printCashSummaryValidator.validate(array);
        assertTrue(result);
    }

    @Test
    public void validateFailIfLengthLess() {
        String[] array = new String[]{""};

        boolean result = printCashSummaryValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfLengthMore() {
        String[] array = new String[]{"?", "USD"};

        boolean result = printCashSummaryValidator.validate(array);
        assertFalse(result);
    }

    @Test
    public void validateFailIfNotQuestion() {
        String[] array = new String[]{"+"};

        boolean result = printCashSummaryValidator.validate(array);
        assertFalse(result);
    }

}
