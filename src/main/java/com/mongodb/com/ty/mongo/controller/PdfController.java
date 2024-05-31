package com.mongodb.com.ty.mongo.controller;

import com.mongodb.com.ty.mongo.Service.HtmlGeneratorService;
import com.mongodb.com.ty.mongo.Service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
//@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private HtmlGeneratorService htmlGeneratorService;



    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadPdf() {

//        htmlGeneratorService.generateHtmlFile();

        try {
            // Convert HTML to PDF
            Path pdfFilePath = pdfService.convertHtmlToPdf();

            // Serve the PDF file
            InputStreamResource inputStreamResource = new InputStreamResource(Files.newInputStream(pdfFilePath));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(pdfFilePath))
                    .body(inputStreamResource);

        } catch (IOException e) {
            System.out.println("Error while generating or reading HTML/PDF file: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
