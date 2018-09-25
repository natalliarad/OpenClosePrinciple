package financeReportInteractor;

import com.sun.istack.internal.NotNull;

/**
 * Class for a particular response model.
 *
 * @author Natallia Radaman
 */
public class ResponseModelReport implements ResponseFinanceReport {

    private final double revenues;
    private final double operatingExpense;
    private final double profit;

    public ResponseModelReport(@NotNull final double revenues,
                               @NotNull final double operatingExpense,
                               @NotNull final double profit) {
        this.revenues = revenues;
        this.operatingExpense = operatingExpense;
        this.profit = profit;
    }

    public double getRevenues() {
        return revenues;
    }

    public double getOperatingExpense() {
        return operatingExpense;
    }

    public double getProfit() {
        return profit;
    }
}
