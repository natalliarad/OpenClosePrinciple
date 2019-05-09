package financeReportInteractor;

/**
 * Interface for polymorphism realisation: there are different types of the response model may be.
 *
 * @author Natallia Radaman
 */
public interface ResponseFinanceReport {

    double getRevenues();

    double getOperatingExpense();

    double getProfit();
}
