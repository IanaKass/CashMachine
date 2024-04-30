package cash.machine.repo;

import cash.machine.exception.UnavailableCashOutException;
import cash.machine.model.CashBanknoteInfo;

import java.util.List;

/**
 * Интерфейс репозитория по работе с банкнотами наличных
 */
public interface CashBanknoteInfoRepo {

    /**
     * Добавление информации о банкноте
     *
     * @param currency валюта
     * @param value номинал банкноты
     * @param number количество банкнот
     * @return Информация об имеющихся банкнотах
     */
    CashBanknoteInfo addCashBanknoteInfo(String currency, Integer value, Integer number);

    /**
     * Выдача имеющихся банкнот
     *
     * @param requiredCurrency требуемая валюта
     * @param requiredAmount требуемая сумма
     * @return список банкнот с номиналом
     * @throws UnavailableCashOutException исключение, если существующих банкнот недостаточно
     */
    List<CashBanknoteInfo> cashOut(String requiredCurrency, Integer requiredAmount)
            throws UnavailableCashOutException;

    /**
     * Получение информации о всех имеющихся банкнотах
     *
     * @return список всех банкнот
     */
    List<CashBanknoteInfo> getCashBanknoteSummary();
}
