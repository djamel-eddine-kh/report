package dz.protid.it.reporting;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

public class PdfReportGeneration implements ReportGenerationType {
    @Override
    public void generateReport(Object object) throws DRException {
        JasperReportBuilder reportBuilder = new ReportingServiceImpl(object).build();
        String filePath = "InvoiceReport.pdf"; // Specify the path where you want to save the PDF
        try {
            reportBuilder.toPdf(new java.io.FileOutputStream(filePath));
            System.out.println("PDF generated successfully at: " + filePath);
        } catch (DRException | java.io.FileNotFoundException e) {
            System.err.println("PDF generation failed: ");
            e.printStackTrace();
        }
    }
}
