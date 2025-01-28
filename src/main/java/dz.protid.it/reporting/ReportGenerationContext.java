package dz.protid.it.reporting;

import net.sf.dynamicreports.report.exception.DRException;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class ReportGenerationContext<T> {
    private ReportGenerationType reportGenerationstrategy;

    public void setStrategy(ReportGenerationType<T> strategy) {
        this.reportGenerationstrategy = strategy;
    }
    public void generateReport(T object) throws DRException {
        if (reportGenerationstrategy == null) {
            throw new IllegalStateException("Report generation strategy is not set!");
        }
        reportGenerationstrategy.generateReport(object);
    }

}
