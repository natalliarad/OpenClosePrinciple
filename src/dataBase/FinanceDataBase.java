package dataBase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FinanceDataBase {
    static final Map<String, BigDecimal> revenues;
    static final Map<String, BigDecimal> operatingExpenses;
    static final Map<String, BigDecimal> profit;

    static {
        revenues = new HashMap<>();
        revenues.put("january", new BigDecimal(15100.99));
        revenues.put("february", new BigDecimal(14700.11));
        revenues.put("march", new BigDecimal(15990.10));
        revenues.put("april", new BigDecimal(13999.99));
        revenues.put("may", new BigDecimal(17100.25));
        revenues.put("june", new BigDecimal(19000.01));
        operatingExpenses = new HashMap<>();
        operatingExpenses.put("january", new BigDecimal(7800.99));
        operatingExpenses.put("february", new BigDecimal(2330.43));
        operatingExpenses.put("march", new BigDecimal(9330.11));
        operatingExpenses.put("april", new BigDecimal(2399.66));
        operatingExpenses.put("may", new BigDecimal(6600.25));
        operatingExpenses.put("june", new BigDecimal(4790.91));
        profit = new HashMap<>();
        profit.put("january", new BigDecimal(6100.29));
        profit.put("february", new BigDecimal(6330.63));
        profit.put("march", new BigDecimal(5930.11));
        profit.put("april", new BigDecimal(7209.66));
        profit.put("may", new BigDecimal(6900.25));
        profit.put("june", new BigDecimal(5990.91));
    }

    static Map<String, BigDecimal> getRevenues() {
        return revenues;
    }

    static Map<String, BigDecimal> getOperatingExpenses() {
        return operatingExpenses;
    }

    static Map<String, BigDecimal> getProfit() {
        return profit;
    }
}
