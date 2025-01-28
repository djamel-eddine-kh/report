package dz.protid.it.controller;


import dz.protid.it.dto.BonPreparationDto;
import dz.protid.it.service.ArticleService;
import jakarta.inject.Named;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.faces.view.ViewScoped;

import java.util.List;

@Named
@ViewScoped
public class ReportDownloadBean {
/*
    @Autowired
    private BonPreparationParDepot reportGenerator;

    @Autowired
    private ArticleService articleService;

    private StreamedContent pdfFile;
    private StreamedContent excelFile;

    public void downloadPdf() {
        List<BonPreparationDto> data = articleService.getInvoice();
        pdfFile = reportGenerator.generateReport("PDF", data);
    }

    public void downloadExcel() {
        List<BonPreparationDto> data = articleService.getInvoice();
        excelFile = reportGenerator.generateReport("Excel", data);
    }

    // Getters
    public StreamedContent getPdfFile() { return pdfFile; }
    public StreamedContent getExcelFile() { return excelFile; }

 */
}