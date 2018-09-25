package financeReportInteractor;

/**
 * Interface for interaction between  between different analyzers which implements {@link ReportAnalyzer}
 * and {@link financeReportController.ReportFinanceController}.
 *
 * @author Natallia Radaman
 */
public interface ReportAnalyzer {

    ResponseFinanceReport analyze(RequestFinanceReport requestFinanceReport);
}
