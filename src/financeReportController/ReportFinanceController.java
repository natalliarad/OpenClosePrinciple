package financeReportController;

import com.sun.istack.internal.NotNull;

import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.ResponseFinanceReport;
import financeReportPresenter.ReportFinancePresenterFactory;

/**
 * This class contains main logic for analyzing data and selection type of view.
 * Invoked from main() method in the EntryPoint.class.
 *
 * @author Natallia Radaman
 */
public class ReportFinanceController {

    private final RequestFinanceReport requestFinanceReport;
    private final ReportAnalyzer reportAnalyzer;

    public ReportFinanceController(@NotNull final RequestFinanceReport requestFinanceReport,
                                   @NotNull final ReportAnalyzer reportAnalyze) {
        this.requestFinanceReport = requestFinanceReport;
        this.reportAnalyzer = reportAnalyze;
    }

    public ResponseFinanceReport analyzeData() {
        return reportAnalyzer.analyze(requestFinanceReport);
    }

    public void showData(@NotNull final ResponseFinanceReport responseFinanceReport,
                         @NotNull final String typeOfReport) {
        final ReportFinancePresenter reportFinancePresenter = ReportFinancePresenterFactory
                .createReportPresenter(typeOfReport, responseFinanceReport);

        if (reportFinancePresenter != null) {
            reportFinancePresenter.showFinanceReport();
        }
    }
}
