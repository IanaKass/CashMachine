package cash.machine.output;

import java.util.List;

/**
 * Интерфейс для средства вывода данных
 */
public interface OutputDataWriter {

    void writeInfo(List<String> strlist);
}
