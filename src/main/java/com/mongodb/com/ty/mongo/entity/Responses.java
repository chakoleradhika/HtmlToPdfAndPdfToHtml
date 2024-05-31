package com.mongodb.com.ty.mongo.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Responses {

    public static ResponseEntity<ResponseStructure> getCreatedResponse(Object object){
        ResponseStructure responseStructure = new ResponseStructure();
        responseStructure.setHttpStatusCode(HttpStatus.CREATED.value());
        responseStructure.setHttpStatus(HttpStatus.CREATED);
        responseStructure.setData(object);
        return new ResponseEntity<ResponseStructure>(responseStructure , HttpStatus.CREATED);
    }

    public static ResponseEntity<ResponseStructure> getFoundResponse(List<Employee> object) {
        ResponseStructure responseStructure = new ResponseStructure();
        responseStructure.setHttpStatusCode(HttpStatus.FOUND.value());
        responseStructure.setHttpStatus(HttpStatus.FOUND);
        responseStructure.setData(object);
        return new ResponseEntity<ResponseStructure>(responseStructure , HttpStatus.FOUND);
    }

    public static ResponseEntity<ResponseStructure> getOkResponse(Object object) {
        ResponseStructure responseStructure = new ResponseStructure();
        responseStructure.setHttpStatusCode(HttpStatus.OK.value());
        responseStructure.setHttpStatus(HttpStatus.OK);
        responseStructure.setData(object);
        return new ResponseEntity<ResponseStructure>(responseStructure , HttpStatus.OK);
    }

    public static ResponseEntity<ResponseStructure> getNotFoundResponse(Object object) {
        ResponseStructure responseStructure = new ResponseStructure();
        responseStructure.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        responseStructure.setHttpStatus(HttpStatus.NOT_FOUND);
        responseStructure.setData(object);
        return new ResponseEntity<ResponseStructure>(responseStructure , HttpStatus.NOT_FOUND);
    }
}
