package cash.machine.service;

import cash.machine.exception.UnavailableCashOutException;
import cash.machine.exception.ValidationException;
import cash.machine.model.CashBanknoteInfo;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.validator.Validator;

import java.util.List;

/**
 * Базовый класс для сервисов обработки команд по работе с наличными банкнотами
 */
public abstract class Service {

    private final CashBanknoteInfoRepo cashRepo;
    private final Validator validator;

    public Service(CashBanknoteInfoRepo cashRepo, Validator validator) {
        this.cashRepo = cashRepo;
        this.validator = validator;
    }

    /**
     * Обработка входной строки
     *
     * @param line строка с информацией
     * @return список обработанных наличных банкнот
     * @throws ValidationException ошибка валидации
     * @throws UnavailableCashOutException ошибка при невозможности выдать запрошенную сумму
     */
    public List<CashBanknoteInfo> process(String line)
            throws ValidationException, UnavailableCashOutException {

        String[] array = line.split(" ");
        if (!validator.validate(array)) {
            throw new ValidationException();
        }
        return getCashInfo(array);
    }

    protected CashBanknoteInfoRepo getCashRepo() {
        return cashRepo;
    }

    protected abstract List<CashBanknoteInfo> getCashInfo(String[] inputArray)
            throws UnavailableCashOutException;

    /**
     * Получение данных для печати
     *
     * @param cashInfoList Список имеющихся банкнот наличных
     * @return список строк для печати
     */
    public abstract List<String> getPrintView(List<CashBanknoteInfo> cashInfoList);
}
