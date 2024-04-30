package cash.machine;

import cash.machine.exception.UnavailableCashOutException;
import cash.machine.exception.ValidationException;
import cash.machine.input.InputDataReader;
import cash.machine.model.CashBanknoteInfo;
import cash.machine.output.OutputConsole;
import cash.machine.output.OutputDataWriter;
import cash.machine.repo.CashBanknoteInfoRepo;
import cash.machine.repo.CashBanknoteInfoRepoImpl;
import cash.machine.service.AddCashService;
import cash.machine.service.CashOutService;
import cash.machine.service.PrintCashSummaryService;
import cash.machine.service.Service;
import cash.machine.input.InputConsole;

import java.util.HashMap;
import java.util.List;

public class Main {

    private static final InputDataReader inConsole = new InputConsole();
    private static final OutputDataWriter outConsole = new OutputConsole();

    private static final CashBanknoteInfoRepo cashRepo = new CashBanknoteInfoRepoImpl();
    private static final String FAIL_MSG = "ERROR";

    private static final HashMap<Character, Service> serviceMap;

    static {
        serviceMap = new HashMap<>(3);
        serviceMap.put('+', new AddCashService(cashRepo));
        serviceMap.put('-', new CashOutService(cashRepo));
        serviceMap.put('?', new PrintCashSummaryService(cashRepo));
    }

    public static void main(String[] args) {

        while (true) {
            if (inConsole.hasNextLine()) {
                String inLine = inConsole.getLine();

                try {
                    Service service = getService(inLine);

                    List<CashBanknoteInfo> cashInfolist = service.process(inLine);
                    List<String> printableList = service.getPrintView(cashInfolist);
                    outConsole.writeInfo(printableList);

                } catch (ValidationException | UnavailableCashOutException e) {
                    System.out.println(FAIL_MSG);
                }
            }
        }
    }

    private static Service getService(String line) throws ValidationException {

        if (line == null || line.isEmpty() || !serviceMap.containsKey(line.charAt(0))) {
            throw new ValidationException();
        }
        return serviceMap.get(line.charAt(0));
    }
}