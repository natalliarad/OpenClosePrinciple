package financeReportInteractor.AnalyzerTypes;

import java.math.BigDecimal;
import java.util.Map;

import financeReportInteractor.FinanceDataProvider;
import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.ResponseFinanceReport;
import financeReportInteractor.ResponseModelReport;
import financeReportInteractor.Taxes;

public class ReportAnalyzerAlgorithm2 implements ReportAnalyzer {

    double revenuesForPeriod;
    double operatingExpensesForPeriod;
    double profitForPeriod;
    private final double riskRate = 0.9;

    private final String REPORT_WITHOUT_TAX = "REPORT_WITHOUT_TAX";
    private final String REPORT_WITH_TAX = "REPORT_WITH_TAX";

    private final FinanceDataProvider financeDataProvider;

    public ReportAnalyzerAlgorithm2(FinanceDataProvider financeDataProvider) {
        this.financeDataProvider = financeDataProvider;
    }

    @Override
    public ResponseFinanceReport analyze(RequestFinanceReport requestFinanceReport) {
        final Map<String, BigDecimal> revenues = financeDataProvider.getRevenues();
        final Map<String, BigDecimal> operatingExpenses = financeDataProvider.getOperatingExpenses();
        final Map<String, BigDecimal> profit = financeDataProvider.getProfit();
        ResponseFinanceReport responseFinanceReport;
        switch (requestFinanceReport.getTypeOfReport()) {
            case REPORT_WITHOUT_TAX:

                for (Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }

                revenuesForPeriod = revenuesForPeriod * riskRate;

                for (Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }

                operatingExpensesForPeriod = operatingExpensesForPeriod * riskRate;

                for (Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriod += entry.getValue().doubleValue();
                }

                profitForPeriod = profitForPeriod * riskRate;

                responseFinanceReport = new ResponseModelReport(revenuesForPeriod, operatingExpensesForPeriod, profitForPeriod);

                return responseFinanceReport;
            case REPORT_WITH_TAX:

                Map<String, Taxes> taxes = financeDataProvider.getTaxes(requestFinanceReport.getRateOfTaxes());
                Double profitForPeriodWithoutTaxes = 0.0;
                Double taxesForPeriod = 0.0;

                for (Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                    revenuesForPeriod += entry.getValue().doubleValue();
                }
                revenuesForPeriod = revenuesForPeriod * riskRate;

                for (Map.Entry<String, BigDecimal> entry : operatingExpenses.entrySet()) {
                    operatingExpensesForPeriod += entry.getValue().doubleValue();
                }
                operatingExpensesForPeriod = operatingExpensesForPeriod * riskRate;

                for (Map.Entry<String, BigDecimal> entry : profit.entrySet()) {
                    profitForPeriodWithoutTaxes += entry.getValue().doubleValue();
                }
                profitForPeriodWithoutTaxes = profitForPeriodWithoutTaxes * riskRate;

                for (Map.Entry<String, Taxes> entry : taxes.entrySet()) {
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
