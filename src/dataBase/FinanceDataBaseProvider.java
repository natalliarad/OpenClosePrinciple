package dataBase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import financeReportInteractor.FinanceDataProvider;
import financeReportInteractor.Taxes;

public class FinanceDataBaseProvider implements FinanceDataProvider {

    public FinanceDataBaseProvider() {
        FinanceDataBase financeDataBase = new FinanceDataBase();

    }

    @Override
    public Map<String, BigDecimal> getRevenues() {
        return FinanceDataBase.getRevenues();
    }

    @Override
    public Map<String, BigDecimal> getOperatingExpenses() {
        return FinanceDataBase.getOperatingExpenses();
    }

    @Override
    public Map<String, BigDecimal> getProfit() {
        return FinanceDataBase.getProfit();
    }

    @Override
    public Map<String, Taxes> getTaxes(final double taxesRate) {
        if (Taxes.isValidTaxRate(taxesRate)) {
            final Map<String, Taxes> taxesMap = new HashMap<>();
            final Map<String, BigDecimal> revenues = FinanceDataBase.getRevenues();

            for (final Map.Entry<String, BigDecimal> entry : revenues.entrySet()) {
                final String month = entry.getKey();
                final Taxes tax = new Taxes(taxesRate, entry.getValue());
                taxesMap.put(month, tax);
            }

            return taxesMap;
        } else {
            return null;
        }
    }
}
