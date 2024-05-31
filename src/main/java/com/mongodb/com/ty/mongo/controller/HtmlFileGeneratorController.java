package com.mongodb.com.ty.mongo.controller;

import com.mongodb.com.ty.mongo.Service.EmployeeService;
import com.mongodb.com.ty.mongo.Service.HtmlGeneratorService;
import com.mongodb.com.ty.mongo.dao.EmployeeDao;
import com.mongodb.com.ty.mongo.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class HtmlFileGeneratorController
{

  private TemplateEngine templateEngine;

    private final EmployeeService employeeService;

    private final HtmlGeneratorService htmlGeneratorService;
    @GetMapping("/generateHtml")
    public ResponseEntity<InputStreamResource>  generateAndDownloadHtml() throws IOException {
//        List<Employee> empolyees= (List<Employee>) employeeService.getAllEmployees().getBody().getData();
        htmlGeneratorService.generateHtmlFile();

        Path filePath = htmlGeneratorService.getHtmlFilePath();

        try{
            InputStreamResource inputStreamResource = new InputStreamResource(Files.newInputStream(filePath));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION , "attachment ; filename = employees.html");

            headers.setContentType(MediaType.TEXT_HTML);

            return ResponseEntity.ok().headers(headers).contentLength(Files.size(filePath)).body(inputStreamResource);

        }catch(IOException e){
            System.out.println("Error while generating or reading html file");
            return ResponseEntity.status(500).body(null);
        }
    }

}
