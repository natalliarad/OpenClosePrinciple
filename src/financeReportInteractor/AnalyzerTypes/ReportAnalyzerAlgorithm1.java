package financeReportInteractor.AnalyzerTypes;

import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.util.Map;

import financeReportInteractor.FinanceDataProvider;
import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.ResponseFinanceReport;
import financeReportInteractor.ResponseModelReport;
import financeReportInteractor.Taxes;

/**
 * Analyzes data via {@link FinanceDataProvider} from {@link FinanceDataProvider}. Uses the
 * fake analysis algorithm number 1.
 *
 * @author Natallia Radaman
 */
public class ReportAnalyzerAlgorithm1 implements ReportAnalyzer {

    private final String REPORT_WITHOUT_TAX = "REPORT_WITHOUT_TAX";
    private final String REPORT_WITH_TAX = "REPORT_WITH_TAX";

    private double revenuesForPeriod;
    private double operatingExpensesForPeriod;
    private double profitForPeriod;

    private final FinanceDataProvider financeDataProvider;

    public ReportAnalyzerAlgorithm1(@NotNull final FinanceDataProvider financeDataProvider) {
        this.financeDataProvider = financeDataProvider;
    }

    @Override
    public ResponseFinanceReport analyze(@NotNull final RequestFinanceReport requestFinanceReport) {
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

                for (final Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriod += entry.getValue().doubleValue();
                }

                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
            case REPORT_WITH_TAX:

                final Map<String, Taxes> taxes = financeDataProvider.getTaxes(requestFinanceReport.getRateOfTaxes());
                double profitForPeriodWithoutTaxes = 0.0;
                double taxesForPeriod = 0.0;

                for (final Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }

                for (final Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }

                for (final Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriodWithoutTaxes += entry.getValue().doubleValue();
                }

                for (final Map.Entry<String, Taxes> entry : taxes.entrySet()) {
                    taxesForPeriod += entry.getValue().getTaxValue().doubleValue();
                }

                profitForPeriod = profitForPeriodWithoutTaxes - taxesForPeriod;

                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
        }

        return null;
    }
}
