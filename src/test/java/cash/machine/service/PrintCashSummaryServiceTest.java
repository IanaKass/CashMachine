package cash.machine.service;

import cash.machine.exception.ValidationException;
import cash.machine.model.CashBanknoteInfo;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.repo.CashBanknoteInfoRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrintCashSummaryServiceTest extends ServiceTest {

    @Mock
    private CashBanknoteInfoRepo cashRepo;
    private PrintCashSummaryService printCashSummaryService;

    @BeforeEach
    void setUp() {
        cashRepo = mock(CashBanknoteInfoRepoImpl.class);

        printCashSummaryService = new PrintCashSummaryService(cashRepo);
    }

    @Test
    public void processSuccess() throws Exception {

        when(cashRepo.getCashBanknoteSummary())
                .thenReturn(getThreeCashBanknoteList());

        List<CashBanknoteInfo> resultList =
                printCashSummaryService.process("?");

        assertEquals(3, resultList.size());
    }

    @Test
    public void processThrowsValidationExceptionIfValidationFail() {

        assertThrows(
                ValidationException.class,
                () -> printCashSummaryService.process("??"));
    }

    @Test
    public void getPrintViewSuccess() {

        List<String> list = printCashSummaryService.getPrintView(
                getNineCashBanknoteInfoList());

        assertEquals(9, list.size());

        assertEquals("QWE 100 6", list.get(0));
        assertEquals("QWE 500 4", list.get(1));
        assertEquals("USD 100 3", list.get(7));
        assertEquals("USD 500 15", list.get(8));
    }
}
