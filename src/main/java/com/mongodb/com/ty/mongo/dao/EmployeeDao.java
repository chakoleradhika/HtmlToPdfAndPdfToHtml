package com.mongodb.com.ty.mongo.dao;

import com.mongodb.com.ty.mongo.entity.Employee;
import com.mongodb.com.ty.mongo.repository.EmployeeRepository;
import com.mongodb.com.ty.mongo.util.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Component
@Repository
@RequiredArgsConstructor
public class EmployeeDao
{
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee emp)
    {
       return employeeRepository.save(emp);
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    public Employee findById(String id)
    {
        return employeeRepository.findById(id).orElseThrow(()->new NoSuchElementException(CommonConstant.EMPLOYEE_NOT_FOUND));
    }

    public Employee deleteEmployee(Employee employee)
    {
        employeeRepository.delete(employee);
        return employee;
    }


//    public List<Employee> findEmployeeByName(String name)
//    {
//     return employeeRepository.findByName(name);
//    }
}
