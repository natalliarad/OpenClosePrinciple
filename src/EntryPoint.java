import dataBase.FinanceDataBaseProvider;
import financeReportController.ReportFinanceController;
import financeReportInteractor.FinanceDataProvider;
import financeReportInteractor.AnalyzerTypes.ReportAnalyzerAlgorithm1;
import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.RequestModelReport;
import financeReportInteractor.ResponseFinanceReport;

public final class EntryPoint {

    public static void main(final String[] args) {
        final FinanceDataProvider financeDataProvider = new FinanceDataBaseProvider();
        final ReportAnalyzer reportAnalyzer = new ReportAnalyzerAlgorithm1(financeDataProvider);
        final RequestFinanceReport requestFinanceReport = new RequestModelReport(.2);
        final ReportFinanceController reportFinanceController = new ReportFinanceController(requestFinanceReport, reportAnalyzer);
        final ResponseFinanceReport responseFinanceReport = reportFinanceController.analyzeData();
        reportFinanceController.showData(responseFinanceReport, "HTML");
        reportFinanceController.showData(responseFinanceReport, "PDF");
    }
}
