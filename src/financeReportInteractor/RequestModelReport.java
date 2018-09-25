package financeReportInteractor;

import com.sun.istack.internal.NotNull;

/**
 * Class for a particular request model.
 *
 * @author Natallia Radaman
 */
public class RequestModelReport implements RequestFinanceReport {

    private final String REPORT_WITHOUT_TAX = "REPORT_WITHOUT_TAX";
    private final String REPORT_WITH_TAX = "REPORT_WITH_TAX";
    private final String typeOfReport;
    private final double rateOfTaxes;

    public RequestModelReport() {
        this.typeOfReport = REPORT_WITHOUT_TAX;
        this.rateOfTaxes = 0;
    }

    public RequestModelReport(@NotNull final double rateOfTaxes) {
        this.typeOfReport = REPORT_WITH_TAX;
        this.rateOfTaxes = rateOfTaxes;
    }

    public String getTypeOfReport() {
        return typeOfReport;
    }

    public double getRateOfTaxes() {
        return rateOfTaxes;
    }
}
