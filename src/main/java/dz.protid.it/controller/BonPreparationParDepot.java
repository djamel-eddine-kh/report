package dz.protid.it.controller;

import dz.protid.it.dto.BonPreparationDto;
import dz.protid.it.reporting.PdfReportGeneration;
import dz.protid.it.reporting.ReportGenerationContext;
import dz.protid.it.reporting.ReportGenerationType;
import dz.protid.it.reporting.XlsReportGeneration;
import dz.protid.it.service.ArticleService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Named
@ViewScoped
public class BonPreparationParDepot {

    private StreamedContent File;

    @Autowired
    private ArticleService articleService;
    public StreamedContent getPdfFile() {
        return File;
    }

    public StreamedContent getFile() {
        return File;
    }

    public void setFile(StreamedContent file) {
        File = file;
    }

    private final ReportGenerationContext<List<BonPreparationDto>> reportGenerationContext;

    public BonPreparationParDepot() {
        this.reportGenerationContext = new ReportGenerationContext<>();
    }

    public StreamedContent generateReport(String reportFormat) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ReportGenerationType<List<BonPreparationDto>> strategy = switch (reportFormat) {
                case "PDF" -> new PdfReportGeneration(outputStream);
                case "Excel" -> new XlsReportGeneration(outputStream);
                default -> throw new IllegalArgumentException("Invalid format");
            };
            List<BonPreparationDto> data = articleService.getInvoice();

            reportGenerationContext.setStrategy(strategy);
            reportGenerationContext.generateReport(data);
File= DefaultStreamedContent.builder()
                    .name("report." + reportFormat.toLowerCase())
                    .contentType(getMimeType(reportFormat))
                    .stream(() -> new ByteArrayInputStream(outputStream.toByteArray()))
                    .build();
            return File;

        } catch (Exception e) {
            throw new RuntimeException("Report generation failed", e);
        }
    }

    private String getMimeType(String format) {
        return switch (format) {
            case "PDF" -> "application/pdf";
            case "Excel" -> "application/vnd.ms-excel";
            default -> "application/octet-stream";
        };
    }
   /* public static void main(String[] args) {
        // Initialize Spring application context
        org.springframework.context.ApplicationContext context =
                new org.springframework.context.annotation.AnnotationConfigApplicationContext("dz.protid.it");

        // Get the InvoiceController bean from the application context
        BonPreparationParDepot controller = context.getBean(BonPreparationParDepot.class);

        ArticleService articleService = context.getBean(ArticleService.class);

        //String sql="SELECT article.code_article, article.designation, article.prix_unitaire, article.quantite, article.prix_total, depot.nom_depot FROM article INNER JOIN depot ON article.depot_id = depot.id";
        // Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
       // List<BonPreparationDto> bonPreparationDto = query.list();

        // Generate the report (you can specify "PDF" or "Excel")
        //controller.generateInvoiceReport("Excel",articleService.getInvoice());


    }*/
    }
