package com.mongodb.com.ty.mongo.controller;

import com.mongodb.com.ty.mongo.Service.EmployeeService;
import com.mongodb.com.ty.mongo.dao.EmployeeDao;
import com.mongodb.com.ty.mongo.dto.EmployeeTo;
import com.mongodb.com.ty.mongo.entity.Employee;
import com.mongodb.com.ty.mongo.entity.ResponseStructure;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    private final EmployeeDao employeeDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseStructure> createEmployee(@RequestBody EmployeeTo employeeTo) {

        return employeeService.createEmployee(employeeTo);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseStructure> getAttEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseStructure> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/names/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseStructure> getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseStructure> deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseStructure> updateEmployee(@RequestBody EmployeeTo employeeTo, @PathVariable String id) {
        return employeeService.updateEmployee(employeeTo, id);
    }

    @GetMapping("/html")
    public ResponseEntity<InputStreamResource> getHtmlFile(HttpServletResponse response) throws IOException {
        List<Employee> emps = employeeDao.getAllEmployees();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\chako\\Downloads\\com.ty.mongo\\com.ty.mongo\\src\\main\\resources\\static/Employee.html"));
        writer.write("<!DOCTYPE html>\n");
        writer.write("<html>\n<head>\n<title>Users</title>\n</head>\n<body>\n");
        writer.write("<h1>Users List</h1>\n");
        writer.write("<table border='1'>\n<tr><th>ID</th><th>Name</th><th>Location</th><th>salary</th></tr>\n");

        for (Employee e : emps) {
            writer.write("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getLocation() + "</td><td>" + e.getSalary() + "</td></tr>\n");
        }

        writer.write("</table>\n</body>\n</html>");
        writer.close();
        Path filePath = getHtmlFilePath();
        System.out.println(filePath);
        try {
            InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.html");
            headers.setContentType(MediaType.TEXT_HTML);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(filePath))
                    .body(resource);

        } catch (IOException e) {
            System.out.println(" Error while generating or reading HTML file");
            return ResponseEntity.status(500).body(null);
        }
    }


    public Path getHtmlFilePath() {
        return Paths.get("C:\\Users\\chako\\Downloads\\com.ty.mongo\\com.ty.mongo\\src\\main\\resources\\static\\Employee.html");

    }
}
