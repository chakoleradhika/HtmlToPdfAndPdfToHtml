package com.mongodb.com.ty.mongo.repository;

import com.mongodb.com.ty.mongo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeeRepository extends MongoRepository<Employee , String> {

    @Query("{name:?0}")
    List<Employee> findByName(String name);
}
