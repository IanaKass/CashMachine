package cash.machine.service;

import cash.machine.exception.ValidationException;
import cash.machine.model.CashBanknoteInfo;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.repo.CashBanknoteInfoRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddCashServiceTest extends ServiceTest {

    @Mock
    private CashBanknoteInfoRepo cashRepo;
    private AddCashService addCashService;

    @BeforeEach
    void setUp() {
        cashRepo = mock(CashBanknoteInfoRepoImpl.class);
        when(cashRepo.addCashBanknoteInfo("QWE", 500, 2))
                .thenReturn(new CashBanknoteInfo("QWE", 500, 2));

        addCashService = new AddCashService(cashRepo);
    }

    @Test
    public void processSuccess() throws Exception {

        List<CashBanknoteInfo> resultList =
                addCashService.process("+ QWE 500 2");

        assertEquals(1, resultList.size());
    }

    @Test
    public void processThrowsValidationExceptionIfValidationFail() {

        assertThrows(
                ValidationException.class,
                () -> addCashService.process("+ QwE 500 2"));
    }

    @Test
    public void getPrintViewSuccess() {

        List<String> list = addCashService.getPrintView(getThreeCashBanknoteList());

        assertEquals(0, list.size());
    }
}
