import java.util.Map;

import dataBase.FinanceDataBaseProvider;
import financeReportInteractor.FinanceDataProvider;
import financeReportInteractor.ReportAnalyzer;
import financeReportInteractor.RequestFinanceReport;
import financeReportInteractor.RequestModelReport;
import financeReportInteractor.ResponseFinanceReport;

public class EntryPoint {
    public static void main(String[] args) {
        FinanceDataProvider financeDataProvider = new FinanceDataBaseProvider();
        ReportAnalyzer reportAnalyzer =new ReportAnalyzer(financeDataProvider);
        RequestFinanceReport requestFinanceReport = new RequestModelReport(.2);
        ResponseFinanceReport result = reportAnalyzer.analyze(requestFinanceReport);
        System.out.println(result.toString());
    }
}
