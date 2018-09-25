package financeReportInteractor;

import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;

/**
 * Class-"entity", that we need to perform analysis.
 *
 * @author Natallia Radaman
 */
public class Taxes {

    private final double taxRate;
    private final BigDecimal revenue;
    private final BigDecimal taxValue;

    public Taxes(@NotNull final double taxRate,
                 @NotNull final BigDecimal revenue) {
        this.taxRate = taxRate;
        this.revenue = revenue;
        final BigDecimal taxValue = revenue.multiply(BigDecimal.valueOf(taxRate));
        this.taxValue = taxValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public static boolean isValidTaxRate(final double taxRate) {

        return taxRate >= 0 & taxRate < 1;
    }
}
