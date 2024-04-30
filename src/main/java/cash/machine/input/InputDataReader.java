package cash.machine.input;

/**
 * Интерфейс для средства чтения данных
 */
public interface InputDataReader {

    boolean hasNextLine();

    String getLine();
}
