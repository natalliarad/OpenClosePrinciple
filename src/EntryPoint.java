import dataBase.FinanceDataBaseProvider;
import financeReportController.ReportFinanceController;
import financeReportInteractor.FinanceDataProvider;
import financeReportInteractor.AnalyzerTypes.ReportAnalyzerAlgorithm1;
import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.RequestModelReport;
import financeReportInteractor.ResponseFinanceReport;

public class EntryPoint {

    public static void main(String[] args) {
        FinanceDataProvider financeDataProvider = new FinanceDataBaseProvider();
        ReportAnalyzer reportAnalyzer = new ReportAnalyzerAlgorithm1(financeDataProvider);
        RequestFinanceReport requestFinanceReport = new RequestModelReport(.2);
        ReportFinanceController reportFinanceController = new ReportFinanceController(requestFinanceReport, reportAnalyzer);
        ResponseFinanceReport responseFinanceReport = reportFinanceController.analyzeData();
        reportFinanceController.showData(responseFinanceReport, "HTML");
        reportFinanceController.showData(responseFinanceReport, "PDF");
    }
}
