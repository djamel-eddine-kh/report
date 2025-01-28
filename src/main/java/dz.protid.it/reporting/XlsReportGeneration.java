package dz.protid.it.reporting;

import dz.protid.it.dto.BonPreparationDto;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XlsReportGeneration implements ReportGenerationType<List<BonPreparationDto>> {
    private final OutputStream outputStream;

    public XlsReportGeneration(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void generateReport(List<BonPreparationDto> object) throws DRException {
        // Build the JasperReport
        JasperReportBuilder reportBuilder = new ReportingServiceImpl(object,true).build();

        // Define the report output directory using NIO Paths
        Path reportDirectory = Paths.get(System.getProperty("user.home"), "reports");

        // Create the directory if it doesn't exist
        try {
            Files.createDirectories(reportDirectory);  // Create the directory if it doesn't exist
        } catch (IOException e) {
            System.err.println("Error creating directories: ");
            e.printStackTrace();
            return;  // Exit the method if directory creation fails
        }

        // Define the file path for the XLS report
        Path filePath = reportDirectory.resolve("InvoiceReport.xls");

        try {
            // Generate the report and save it to the specified file path
            reportBuilder.toXls(Files.newOutputStream(filePath));  // Use Files.newOutputStream() for better file handling
            System.out.println("XLS generated successfully at: " + filePath.toString());
        } catch (DRException | IOException e) {
            System.err.println("XLS generation failed: ");
            e.printStackTrace();
        }
    }
}
