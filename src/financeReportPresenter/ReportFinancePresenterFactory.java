package financeReportPresenter;

import com.sun.istack.internal.Nullable;

import financeReportController.ReportFinancePresenter;
import financeReportInteractor.ResponseFinanceReport;
import financeReportPresenter.HTML.ReportFinancePresenterHTML;
import financeReportPresenter.PDF.ReportFinancePresenterPDF;

public final class ReportFinancePresenterFactory {

    private static final String TYPE_REPORT_HTML = "HTML";
    private static final String TYPE_REPORT_PDF = "PDF";

    @Nullable
    public static ReportFinancePresenter createReportPresenter(final String typeOfPresenter,
                                                               final ResponseFinanceReport responseFinanceReport) {
        switch (typeOfPresenter) {
            case TYPE_REPORT_HTML:

                return new ReportFinancePresenterHTML(responseFinanceReport);
            case TYPE_REPORT_PDF:

                return new ReportFinancePresenterPDF(responseFinanceReport);
        }

        return null;
    }
}
