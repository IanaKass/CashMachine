package cash.machine.service;

import cash.machine.exception.UnavailableCashOutException;
import cash.machine.model.CashBanknoteInfo;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.validator.CashOutValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис выдачи наличных
 */
public class CashOutService extends Service {

    private static final String PRINT_VIEW_FORMAT = "%d %d";

    public CashOutService(CashBanknoteInfoRepo cashRepo) {
        super(cashRepo, new CashOutValidator());
    }

    @Override
    protected List<CashBanknoteInfo> getCashInfo(String[] inputArray)
            throws UnavailableCashOutException {
        return getCashRepo().cashOut(inputArray[1], Integer.valueOf(inputArray[2]));
    }

    @Override
    public List<String> getPrintView(List<CashBanknoteInfo> cashInfoList) {
        List<String> strList = new ArrayList<>(cashInfoList.size());
        for (CashBanknoteInfo cashInfo : cashInfoList) {
            strList.add(
                    String.format(
                            PRINT_VIEW_FORMAT,
                            cashInfo.value(),
                            cashInfo.number()
                    )
            );
        }
        return strList;
    }
}
