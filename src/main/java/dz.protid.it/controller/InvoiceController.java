package dz.protid.it.controller;

import dz.protid.it.reporting.PdfReportGeneration;
import dz.protid.it.reporting.ReportGenerationContext;
import dz.protid.it.reporting.ReportGenerationType;
import dz.protid.it.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceController {

        private ReportGenerationContext reportGenerationContext;
        @Autowired
        private final ArticleService articleService;

        public InvoiceController(ArticleService articleService) {
            this.articleService = articleService;
            // Instantiate the context
            this.reportGenerationContext = new ReportGenerationContext();
        }

        // Generate the invoice report based on the specified format (PDF, Excel, etc.)
        public void generateInvoiceReport( String reportFormat) {
            try {
                ReportGenerationType strategy;

                    strategy = new PdfReportGeneration();


                // Set the strategy dynamically
                reportGenerationContext.setStrategy(strategy);

                // Generate the report using the selected strategy
                reportGenerationContext.generateReport(articleService.getInvoice());

            } catch (Exception e) {
                System.err.println("Error generating report: ");
                e.printStackTrace();
            }
        }


    public static void main(String[] args) {
        // Initialize Spring application context
        org.springframework.context.ApplicationContext context =
                new org.springframework.context.annotation.AnnotationConfigApplicationContext("dz.protid.it");

        // Get the InvoiceController bean from the application context
        InvoiceController controller = context.getBean(InvoiceController.class);

        // Generate the report (you can specify "PDF" or "Excel")
        controller.generateInvoiceReport("PDF");
    }
    }
