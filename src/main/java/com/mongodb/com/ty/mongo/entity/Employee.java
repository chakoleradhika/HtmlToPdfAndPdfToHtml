package com.mongodb.com.ty.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Document(value = "employee")
public class Employee
{
    @Id
    private String id;
    @Field(name="Emp_name")
    private String name;
    private String location;
    private BigDecimal salary;

}
