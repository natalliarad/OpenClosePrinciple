package financeReportInteractor;

import java.math.BigDecimal;
import java.util.Map;

public interface FinanceDataProvider {
    Map<String, BigDecimal> getRevenues();
    Map<String, BigDecimal> getOperatingExpenses();
    Map<String, BigDecimal> getProfit();
    Map<String, Taxes> getTaxes(double taxesRate);
}
