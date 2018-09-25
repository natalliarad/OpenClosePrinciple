package financeReportPresenter.PDF;

import financeReportController.ReportFinancePresenter;
import financeReportInteractor.ResponseFinanceReport;

public class ReportFinancePresenterPDF implements ReportFinancePresenter {

    private ResponseFinanceReport responseFinanceReport;

    public ReportFinancePresenterPDF(ResponseFinanceReport responseFinanceReport) {
        this.responseFinanceReport = responseFinanceReport;
    }

    @Override
    public void showFinanceReport() {
        System.out.println("\n\nYou have chosen to create a report in the form of PDF page.\n\n");
        System.out.println(" ");
        System.out.println("____________________PDF________PDF_________________________");
        System.out.println(" ");
        System.out.println("The revenues for the period are " + responseFinanceReport.getRevenues());
        System.out.println("____________________PDF________PDF_________________________");
        System.out.println(" ");
        System.out.println("The operating expenses for the period are " + responseFinanceReport.getOperatingExpense());
        System.out.println("____________________PDF________PDF_________________________");
        System.out.println(" ");
        System.out.println("The profit for the period is " + responseFinanceReport.getProfit());
        System.out.println(" ");
        System.out.println("____________________PDF________PDF_________________________");
        System.out.println(" ");
        System.out.println("\nThan you fot your attention.\n\n");
    }
}
