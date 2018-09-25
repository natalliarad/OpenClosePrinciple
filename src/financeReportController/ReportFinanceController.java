package financeReportController;

import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.ResponseFinanceReport;
import financeReportPresenter.HTML.ReportFinancePresenterHTML;
import financeReportPresenter.PDF.ReportFinancePresenterPDF;

public class ReportFinanceController {

    private RequestFinanceReport requestFinanceReport;
    private ReportAnalyzer reportAnalyzer;
    private ReportFinancePresenter reportFinancePresenter;
    private final String TYPE_REPORT_HTML = "HTML";
    private final String TYPE_REPORT_PDF = "PDF";

    public ReportFinanceController(RequestFinanceReport requestFinanceReport, ReportAnalyzer reportAnalyze) {
        this.requestFinanceReport = requestFinanceReport;
        this.reportAnalyzer = reportAnalyze;
    }

    public ResponseFinanceReport analyzeData() {
        return reportAnalyzer.analyze(requestFinanceReport);
    }

    public void showData(ResponseFinanceReport responseFinanceReport, String typeOfReport) {
        reportFinancePresenter = null;
        switch (typeOfReport) {
            case TYPE_REPORT_HTML:
                reportFinancePresenter = new ReportFinancePresenterHTML(responseFinanceReport);
                break;
            case TYPE_REPORT_PDF:
                reportFinancePresenter = new ReportFinancePresenterPDF(responseFinanceReport);
                break;
        }
        if (reportFinancePresenter !=null)
            reportFinancePresenter.showFinanceReport();
    }
}
