package com.mongodb.com.ty.mongo.dao;

import com.mongodb.com.ty.mongo.dto.EmployeeTo;
import com.mongodb.com.ty.mongo.entity.Employee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MongoTemplateEmployeeDao
{
    private final MongoTemplate mongoTemplate ;

    private final ModelMapper modelMapper;

    public Employee createEmployee(Employee employee){
        return mongoTemplate.save(employee);
    }

    public Employee findById(String id){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));

//        Employee employee = mongoTemplate.findById(id,Employee.class);
//        return employee;

        return mongoTemplate.findById(id,Employee.class);
    }

    public List<Employee> findEmployeeByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query , Employee.class);
    }



    public Employee deleteEmployee(Employee employee){
        mongoTemplate.remove(employee);
        return employee;
    }


    public List<Employee> getAllEmployees(){
        return mongoTemplate.findAll(Employee.class);
    }
}
