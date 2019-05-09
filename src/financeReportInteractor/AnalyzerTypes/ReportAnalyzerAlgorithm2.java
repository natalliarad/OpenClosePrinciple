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
 * fake analysis algorithm number 2.
 *
 * @author Natallia Radaman
 */
public class ReportAnalyzerAlgorithm2 implements ReportAnalyzer {

    private final String REPORT_WITHOUT_TAX = "REPORT_WITHOUT_TAX";
    private final String REPORT_WITH_TAX = "REPORT_WITH_TAX";

    private double revenuesForPeriod;
    private double operatingExpensesForPeriod;
    private double profitForPeriod;

    private final FinanceDataProvider financeDataProvider;

    public ReportAnalyzerAlgorithm2(@NotNull final FinanceDataProvider financeDataProvider) {
        this.financeDataProvider = financeDataProvider;
    }

    @Override
    public ResponseFinanceReport analyze(@NotNull final RequestFinanceReport requestFinanceReport) {
        final Map<String, BigDecimal> revenues = financeDataProvider.getRevenues();
        final Map<String, BigDecimal> operatingExpenses = financeDataProvider.getOperatingExpenses();
        final Map<String, BigDecimal> profit = financeDataProvider.getProfit();
        final ResponseFinanceReport responseFinanceReport;
        final double riskRate = 0.9;

        switch (requestFinanceReport.getTypeOfReport()) {
            case REPORT_WITHOUT_TAX:

                for (final Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }

                revenuesForPeriod = revenuesForPeriod * riskRate;

                for (final Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }

                operatingExpensesForPeriod = operatingExpensesForPeriod * riskRate;

                for (final Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriod += entry.getValue().doubleValue();
                }

                profitForPeriod = profitForPeriod * riskRate;

                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
            case REPORT_WITH_TAX:

                final Map<String, Taxes> taxes = financeDataProvider.getTaxes(requestFinanceReport.getRateOfTaxes());
                Double profitForPeriodWithoutTaxes = 0.0;
                Double taxesForPeriod = 0.0;

                for (final Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }

                revenuesForPeriod = revenuesForPeriod * riskRate;

                for (final Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }

                operatingExpensesForPeriod = operatingExpensesForPeriod * riskRate;

                for (final Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriodWithoutTaxes += entry.getValue().doubleValue();
                }

                profitForPeriodWithoutTaxes = profitForPeriodWithoutTaxes * riskRate;

                for (final Map.Entry<String, Taxes> entry : taxes.entrySet()) {
                    taxesForPeriod += entry.getValue().getTaxValue().doubleValue();
                }

                taxesForPeriod = taxesForPeriod * riskRate;

                profitForPeriod = profitForPeriodWithoutTaxes - taxesForPeriod;
                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
        }

        return null;
    }
}
