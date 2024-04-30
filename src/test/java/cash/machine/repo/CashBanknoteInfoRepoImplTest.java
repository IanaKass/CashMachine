package cash.machine.repo;

import cash.machine.exception.UnavailableCashOutException;
import cash.machine.model.CashBanknoteInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CashBanknoteInfoRepoImplTest {

    private CashBanknoteInfoRepoImpl cashRepo;

    @BeforeEach
    void setUp() {
        cashRepo = new CashBanknoteInfoRepoImpl();
    }

    @Test
    public void addCashBanknoteInfoSuccess() {

        CashBanknoteInfo cashInfo =
                cashRepo.addCashBanknoteInfo("QWE", 1000, 5);

        assertEquals("QWE", cashInfo.currency());
        assertEquals(1000, cashInfo.value());
        assertEquals(5, cashInfo.number());
    }

    @Test
    public void addCashBanknoteInfoTwiceSuccess() {

        CashBanknoteInfo cashInfo =
                cashRepo.addCashBanknoteInfo("USD", 1000, 5);

        assertEquals(5, cashInfo.number());

        cashInfo =
                cashRepo.addCashBanknoteInfo("USD", 1000, 3);

        assertEquals(8, cashInfo.number());
    }

    @Test
    public void cashOutThrowsUnavailableExceptionIfNotCash() {

        assertThrows(
                UnavailableCashOutException.class,
                () -> cashRepo.cashOut("ASD", 700));
    }

    @Test
    public void cashOutSuccess() throws UnavailableCashOutException {

        cashRepo.addCashBanknoteInfo("ZXC", 5000, 2);
        cashRepo.addCashBanknoteInfo("ZXC", 100, 22);

        List<CashBanknoteInfo> cashInfoList =
                cashRepo.cashOut("ZXC", 7000);

        assertEquals(2, cashInfoList.size());

        cashInfoList.sort(
                Comparator.comparing(CashBanknoteInfo::value)
        );

        assertEquals(100, cashInfoList.get(0).value());
        assertEquals(20, cashInfoList.get(0).number());

        assertEquals(5000, cashInfoList.get(1).value());
        assertEquals(1, cashInfoList.get(1).number());
    }

    @Test
    public void getCashBanknoteSummarySuccess() {
        List<CashBanknoteInfo> cashInfoList =
                cashRepo.getCashBanknoteSummary();
        assertNotEquals(0, cashInfoList.size());
    }
}
