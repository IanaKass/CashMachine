package cash.machine.model;

/**
 * Информация об имеющихся банкнотах наличных
 *
 * @param currency валюта
 * @param value номинал банкнот
 * @param number количество банкнот
 */
public record CashBanknoteInfo(String currency, Integer value, Integer number) {
}
