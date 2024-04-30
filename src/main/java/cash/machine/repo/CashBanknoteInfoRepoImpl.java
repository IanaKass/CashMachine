package cash.machine.repo;

import cash.machine.exception.UnavailableCashOutException;
import cash.machine.model.CashBanknoteInfo;

import java.util.*;

/**
 * Репозиторий по работе с банкнотами наличных
 */
public class CashBanknoteInfoRepoImpl implements CashBanknoteInfoRepo {

    private static final Map<String, Map<Integer, Integer>> cashMap = new HashMap<>();

    @Override
    public CashBanknoteInfo addCashBanknoteInfo(String currency, Integer value, Integer number) {
        Map<Integer, Integer> valueMap = cashMap.getOrDefault(currency, new HashMap<>());
        Integer newNumber = valueMap.getOrDefault(value, 0) + number;

        valueMap.put(value, newNumber);
        cashMap.put(currency, valueMap);
        return new CashBanknoteInfo(currency, value, newNumber);
    }

    @Override
    public List<CashBanknoteInfo> cashOut(String requiredCurrency, Integer requiredAmount)
            throws UnavailableCashOutException {

        Map<Integer, Integer> currencyMap = cashMap.get(requiredCurrency);
        if (currencyMap == null || currencyMap.isEmpty()) {
            throw new UnavailableCashOutException();
        }

        List<CashBanknoteInfo> currencyInfoList = currencyMap
                .entrySet()
                .stream()
                .map(e -> new CashBanknoteInfo(requiredCurrency, e.getKey(), e.getValue()))
                .sorted(
                        Comparator.comparing(CashBanknoteInfo::value).reversed()
                ).toList();

        return getCashOutBanknoteInfo(currencyInfoList, requiredCurrency, requiredAmount);
    }

    private List<CashBanknoteInfo> getCashOutBanknoteInfo(List<CashBanknoteInfo> currencyInfoList,
            String requiredCurrency, Integer requiredAmount) throws UnavailableCashOutException {

        List<CashBanknoteInfo> cashOutList = new ArrayList<>();
        Integer restAmount = requiredAmount;
        Map<Integer, Integer> valueMap = new HashMap<>();

        for (CashBanknoteInfo cashInfo : currencyInfoList) {

            if (restAmount == 0) {
                valueMap.put(cashInfo.value(), cashInfo.number());
            } else {
                int intDivPart = restAmount / cashInfo.value();

                if (intDivPart == 0) {
                    valueMap.put(cashInfo.value(), cashInfo.number());
                } else {
                    int cashOutValue;
                    if (intDivPart < cashInfo.number()) {
                        cashOutValue = intDivPart;
                        valueMap.put(cashInfo.value(), cashInfo.number() - intDivPart);
                    } else {
                        cashOutValue = cashInfo.number();
                    }
                    restAmount = restAmount - cashOutValue * cashInfo.value();
                    cashOutList.add(
                            new CashBanknoteInfo(requiredCurrency, cashInfo.value(), cashOutValue));
                }
            }
        }
        if (restAmount > 0) {
            throw new UnavailableCashOutException();
        }

        cashMap.put(requiredCurrency, valueMap);
        return cashOutList;
    }

    @Override
    public List<CashBanknoteInfo> getCashBanknoteSummary() {
        List<CashBanknoteInfo> cashInfoList = new ArrayList<>(cashMap.size());

        for (Map.Entry<String, Map<Integer, Integer>> entry : cashMap.entrySet()) {
            String currency = entry.getKey();

            cashInfoList.addAll(
                    entry.getValue().entrySet()
                        .stream()
                        .map(e -> new CashBanknoteInfo(currency, e.getKey(), e.getValue()))
                        .toList()
            );
        }

        return cashInfoList;
    }
}
