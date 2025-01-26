package dz.protid.it.reporting;

import net.sf.dynamicreports.report.exception.DRException;

public class ReportGenerationContext {
    private ReportGenerationType reportGenerationstrategy;

    public void setStrategy(ReportGenerationType strategy) {
        this.reportGenerationstrategy = strategy;
    }
    public void generateReport(Object object) throws DRException {
        if (reportGenerationstrategy == null) {
            throw new IllegalStateException("Report generation strategy is not set!");
        }
        reportGenerationstrategy.generateReport(object);
    }

}
