package financeReportInteractor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ReportAnalyzer implements ReportAnalyzerBoundary {

    Double revenuesForPeriod = 0.0;
    Double operatingExpensesForPeriod = 0.0;
    Double profitForPeriod = 0.0;

    private final String REPORT_WITHOUT_TAX = "REPORT_WITHOUT_TAX";
    private final String REPORT_WITH_TAX = "REPORT_WITH_TAX";

    private final FinanceDataProvider financeDataProvider;

    public ReportAnalyzer(FinanceDataProvider financeDataProvider) {
        this.financeDataProvider = financeDataProvider;
    }

    @Override
    public ResponseFinanceReport analyze(final RequestFinanceReport requestFinanceReport) {
        final Map<String, BigDecimal> revenues = financeDataProvider.getRevenues();
        final Map<String, BigDecimal> operatingExpenses = financeDataProvider.getOperatingExpenses();
        final Map<String, BigDecimal> profit = financeDataProvider.getProfit();
        final ResponseFinanceReport responseFinanceReport;
        switch (requestFinanceReport.getTypeOfReport()) {
            case REPORT_WITHOUT_TAX:

                for (final Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }

                for (final Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }

                for (Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriod += entry.getValue().doubleValue();
                }

                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
            case REPORT_WITH_TAX:

                Map<String, Taxes> taxes = financeDataProvider.getTaxes(requestFinanceReport.getRateOfTaxes());
                Double profitForPeriodWithoutTaxes = 0.0;
                Double taxesForPeriod = 0.0;

                for (Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }

                for (Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }

                for (Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriodWithoutTaxes += entry.getValue().doubleValue();
                }

                for (Map.Entry<String, Taxes> entry : taxes.entrySet()) {
                    taxesForPeriod += entry.getValue().getTaxValue().doubleValue();
                }

                profitForPeriod = profitForPeriodWithoutTaxes - taxesForPeriod;

                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
        }

        return null;
    }
}
