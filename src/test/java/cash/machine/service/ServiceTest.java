package cash.machine.service;

import cash.machine.model.CashBanknoteInfo;

import java.util.Arrays;
import java.util.List;

public class ServiceTest {

    protected List<CashBanknoteInfo> getThreeCashBanknoteList() {
        return Arrays.asList(
                new CashBanknoteInfo("QWE", 1000, 1),
                new CashBanknoteInfo("QWE", 500, 1),
                new CashBanknoteInfo("QWE", 100, 2)
        );
    }

    protected List<CashBanknoteInfo> getNineCashBanknoteInfoList() {
        return Arrays.asList(
                new CashBanknoteInfo("QWE", 1000, 1),
                new CashBanknoteInfo("USD", 50, 2),
                new CashBanknoteInfo("USD", 100, 3),
                new CashBanknoteInfo("QWE", 100, 6),
                new CashBanknoteInfo("RUB", 100, 10),
                new CashBanknoteInfo("RUB", 5, 12),
                new CashBanknoteInfo("USD", 500, 15),
                new CashBanknoteInfo("RUB", 1000, 6),
                new CashBanknoteInfo("QWE", 500, 4)
        );
    }
}
