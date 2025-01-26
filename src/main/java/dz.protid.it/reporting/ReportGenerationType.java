package dz.protid.it.reporting;

import net.sf.dynamicreports.report.exception.DRException;

public interface ReportGenerationType {
    void generateReport(Object object) throws DRException;
}
