package cash.machine.input;

import java.util.Scanner;

/**
 * Консоль ввода данных
 */
public class InputConsole implements InputDataReader {

    private Scanner in;

    public InputConsole() {
        this.in = new Scanner(System.in);
    }

    public boolean hasNextLine() {
        return in.hasNextLine();
    }

    public String getLine() {
        return in.nextLine();
    }

}
