package dz.protid.it.reporting;

import net.sf.dynamicreports.report.exception.DRException;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface ReportGenerationType<T> {
    void generateReport(T object) throws DRException;
}
