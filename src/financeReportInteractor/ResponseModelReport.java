package financeReportInteractor;

public class ResponseModelReport implements ResponseFinanceReport {
    private final double revenues;
    private final double operatingExpense;
    private final double profit;

    public ResponseModelReport(double revenues, double operatingExpense, double profit) {
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
