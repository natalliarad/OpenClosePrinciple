package financeReportInteractor;

/**
 * Interface for polymorphism realisation: there are different types of the request model may be.
 *
 * @author Natallia Radaman
 */
public interface RequestFinanceReport {

    String getTypeOfReport();

    double getRateOfTaxes();
}
