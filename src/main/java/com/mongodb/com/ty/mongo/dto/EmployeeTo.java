package com.mongodb.com.ty.mongo.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Builder
public class EmployeeTo
{
    private String id;
    private String name;
    private String location;
    private BigDecimal salary;
}
