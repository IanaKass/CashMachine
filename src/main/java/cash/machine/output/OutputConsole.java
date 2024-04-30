package cash.machine.output;

import java.util.List;

/**
 * Консоль вывода данных
 */
public class OutputConsole implements OutputDataWriter {

    @Override
    public void writeInfo(List<String> strlist) {
        for (String str : strlist) {
            System.out.println(str);
        }
        System.out.println("OK");
    }
}
