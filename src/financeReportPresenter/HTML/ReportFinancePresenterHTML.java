package financeReportPresenter.HTML;

import com.sun.istack.internal.NotNull;

import financeReportController.ReportFinancePresenter;
import financeReportInteractor.ResponseFinanceReport;

/**
 * Generates fake "HTML" report, interacts with {@link financeReportController.ReportFinanceController}
 * via {@link ReportFinancePresenter} interface.
 *
 * @author Natallia Radaman
 */
public class ReportFinancePresenterHTML implements ReportFinancePresenter {

    private final ResponseFinanceReport responseFinanceReport;

    public ReportFinancePresenterHTML(@NotNull final ResponseFinanceReport responseFinanceReport) {
        this.responseFinanceReport = responseFinanceReport;
    }

    @Override
    public void showFinanceReport() {
        System.out.println("\n\nYou have chosen to create a report in the form of HTML page.\n\n");
        System.out.println("______________________HTML__________HTML____________________");
        System.out.println(" ");
        System.out.println("The revenues for the period are " + responseFinanceReport.getRevenues());
        System.out.println(" ");
        System.out.println("______________________HTML__________HTML____________________");
        System.out.println(" ");
        System.out.println("The operating expenses for the period are " + responseFinanceReport.getOperatingExpense());
        System.out.println(" ");
        System.out.println("______________________HTML__________HTML____________________");
        System.out.println(" ");
        System.out.println("The profit for the period is " + responseFinanceReport.getProfit());
        System.out.println(" ");
        System.out.println("______________________HTML__________HTML____________________");
        System.out.println(" ");
        System.out.println("\nThan you fot your attention.\n\n");
    }
}
