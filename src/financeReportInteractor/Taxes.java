package financeReportInteractor;

import java.math.BigDecimal;

public class Taxes {

    private double taxRate;
    private final BigDecimal revenue;
    private BigDecimal taxValue;

    public Taxes(final double taxRate, final BigDecimal revenue) {
        this.taxRate = taxRate;
        this.revenue = revenue;
        final BigDecimal taxValue = revenue.multiply(BigDecimal.valueOf(taxRate));
        this.taxValue = taxValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(final int taxRate) {
        this.taxRate = taxRate;
        this.taxValue = revenue.multiply(BigDecimal.valueOf(taxRate));
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public static boolean isValidTaxRate(final double taxRate) {
        final boolean b = taxRate >= 0 & taxRate < 1;

        return b;
    }
}
