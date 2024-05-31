package com.mongodb.com.ty.mongo.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class PdfService
{

    private static final Path HTML = Paths.get("output/employees.html");
    private static final Path PDF = Paths.get("output/employees.pdf");

//    String html = "<html><head><title>Test</title></head><body><h1>Hello, World!</h1></body></html>";

    public Path convertHtmlToPdf() throws IOException {

        File pdfOutputFile = new File("output/employees.pdf");

        try (FileOutputStream os = new FileOutputStream(pdfOutputFile) ) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri(HTML.toUri().toString());
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            System.out.println("error");
            throw new IOException("Error converting HTML to PDF", e);
        }
        return PDF;
    }
}
