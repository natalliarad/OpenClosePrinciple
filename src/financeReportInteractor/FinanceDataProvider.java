package financeReportInteractor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Interface for interaction between different analyzers which implements {@link ReportAnalyzer}
 * interface and {@link FinanceDataProvider}.
 *
 * @author Natallia Radaman
 */
public interface FinanceDataProvider {

    Map<String, BigDecimal> getRevenues();

    Map<String, BigDecimal> getOperatingExpenses();

    Map<String, BigDecimal> getProfit();

    Map<String, Taxes> getTaxes(double taxesRate);
}
