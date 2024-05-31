package com.mongodb.com.ty.mongo.Service;

import com.mongodb.com.ty.mongo.controller.EmployeeController;
import com.mongodb.com.ty.mongo.dao.EmployeeDao;
import com.mongodb.com.ty.mongo.dao.MongoTemplateEmployeeDao;
import com.mongodb.com.ty.mongo.dto.EmployeeTo;
import com.mongodb.com.ty.mongo.entity.Employee;
import com.mongodb.com.ty.mongo.entity.ResponseStructure;
import com.mongodb.com.ty.mongo.entity.Responses;
import com.mongodb.com.ty.mongo.util.CommonConstant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService
{
    private final MongoTemplateEmployeeDao employeeDao;

    private final ModelMapper modelMapper;

    public ResponseEntity<ResponseStructure> createEmployee(EmployeeTo employeeTo) {

        Employee emp = modelMapper.map(employeeTo , Employee.class);
        return Responses.getCreatedResponse(employeeDao.createEmployee(emp));
    }

    public ResponseEntity<ResponseStructure> getAllEmployees(){

        List<Employee> emps =employeeDao.getAllEmployees();

        if(!CollectionUtils.isEmpty(emps)){
            return Responses.getFoundResponse(emps);
        }
        return Responses.getNotFoundResponse(CommonConstant.EMPLOYEE_NOT_FOUND);
    }

    public ResponseEntity<ResponseStructure> deleteEmployee(String id)
    {
        Employee optEmp = employeeDao.findById(id);
        return Responses.getOkResponse(employeeDao.deleteEmployee(optEmp));
    }

    public ResponseEntity<ResponseStructure> updateEmployee(EmployeeTo employeeTo , String id)
    {
       Employee opt = employeeDao.findById(id);
       modelMapper.map(employeeTo , opt);
       return Responses.getOkResponse( employeeDao.createEmployee(opt));
    }

    public ResponseEntity<ResponseStructure> getEmployee(String id)
    {
        return Responses.getOkResponse(employeeDao.findById(id));
    }

    public ResponseEntity<ResponseStructure> getEmployeeByName(String name)
    {
        return Responses.getFoundResponse(employeeDao.findEmployeeByName(name));
   }
}
