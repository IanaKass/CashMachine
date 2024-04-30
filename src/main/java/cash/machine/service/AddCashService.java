package cash.machine.service;

import cash.machine.model.CashBanknoteInfo;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.validator.AddCashValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Сервис добавления данных о банкнотах
 */
public class AddCashService extends Service {

    public AddCashService(CashBanknoteInfoRepo cashRepo) {
        super(cashRepo, new AddCashValidator());
    }

    @Override
    protected List<CashBanknoteInfo> getCashInfo(String[] inputArray) {
        return Arrays.asList(
                getCashRepo().addCashBanknoteInfo(
                        inputArray[1],
                        Integer.valueOf(inputArray[2]),
                        Integer.valueOf(inputArray[3]))
        );
    }

    @Override
    public List<String> getPrintView(List<CashBanknoteInfo> cashInfoList) {
        return Collections.emptyList();
    }
}
