package cash.machine.service;

import cash.machine.exception.UnavailableCashOutException;
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

public class CashOutServiceTest extends ServiceTest {

    @Mock
    private CashBanknoteInfoRepo cashRepo;
    private CashOutService cashOutService;

    @BeforeEach
    void setUp() {
        cashRepo = mock(CashBanknoteInfoRepoImpl.class);

        cashOutService = new CashOutService(cashRepo);
    }

    @Test
    public void processSuccess() throws Exception {

        when(cashRepo.cashOut("QWE", 1700))
                .thenReturn(getThreeCashBanknoteList());

        List<CashBanknoteInfo> resultList =
                cashOutService.process("- QWE 1700");

        assertEquals(3, resultList.size());
    }

    @Test
    public void processThrowsValidationExceptionIfValidationFail() {

        assertThrows(
                ValidationException.class,
                () -> cashOutService.process("- qwe 500"));
    }

    @Test
    public void processThrowsUnavailableExceptionIfNotCash() throws UnavailableCashOutException {
        when(cashRepo.cashOut("QWE", 500))
                .thenThrow(new UnavailableCashOutException());

        assertThrows(
                UnavailableCashOutException.class,
                () -> cashOutService.process("- QWE 500"));
    }

    @Test
    public void getPrintViewSuccess() {

        List<String> list = cashOutService.getPrintView(getThreeCashBanknoteList());

        assertEquals(3, list.size());

        assertEquals("1000 1", list.get(0));
        assertEquals("500 1", list.get(1));
        assertEquals("100 2", list.get(2));
    }
}
