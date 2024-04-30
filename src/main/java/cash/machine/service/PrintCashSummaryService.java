package cash.machine.service;

import cash.machine.model.CashBanknoteInfo;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.validator.PrintCashSummaryValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Сервис печати данных о всех имеющихся наличных
 */
public class PrintCashSummaryService extends Service {

    private static final String PRINT_VIEW_FORMAT = "%s %d %d";

    public PrintCashSummaryService(CashBanknoteInfoRepo cashRepo) {
        super(cashRepo, new PrintCashSummaryValidator());
    }

    @Override
    protected List<CashBanknoteInfo> getCashInfo(String[] inputArray) {
        return getCashRepo().getCashBanknoteSummary();
    }

    @Override
    public List<String> getPrintView(List<CashBanknoteInfo> cashInfoList) {
        cashInfoList.sort(
                Comparator.comparing(CashBanknoteInfo::currency)
                        .thenComparing(CashBanknoteInfo::value)
        );
        List<String> printStrList = new ArrayList<>(cashInfoList.size());
        for (CashBanknoteInfo cashInfo : cashInfoList) {
            printStrList.add(
                    String.format(
                            PRINT_VIEW_FORMAT,
                            cashInfo.currency(),
                            cashInfo.value(),
                            cashInfo.number()
                    )
            );
        }
        return printStrList;
    }
}
