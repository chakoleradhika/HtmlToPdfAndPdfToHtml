package com.mongodb.com.ty.mongo.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
public class ResponseStructure
{
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private Object data;
}
