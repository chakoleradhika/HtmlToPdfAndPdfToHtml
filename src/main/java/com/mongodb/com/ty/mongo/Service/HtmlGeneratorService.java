package com.mongodb.com.ty.mongo.Service;

import com.mongodb.com.ty.mongo.dao.EmployeeDao;
import com.mongodb.com.ty.mongo.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class HtmlGeneratorService {

    private final TemplateEngine templateEngine;

    private final EmployeeDao employeeDao;
    public void generateHtmlFile(){

        List<Employee> employees = employeeDao.getAllEmployees();
        Context context = new Context();
        context.setVariable("employees", employees);

        String htmlContent = templateEngine.process("employees",context);
        System.out.println(htmlContent);

        File outputDirectory = new File("output");

        if(!outputDirectory.exists()){
            outputDirectory.mkdir();
        }

        File outputFile = new File(outputDirectory , "employees.html");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            writer.write(htmlContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Path getHtmlFilePath(){
        return Paths.get("output/employees.html");
    }
}
