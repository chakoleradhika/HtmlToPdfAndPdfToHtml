package com.mongodb.com.ty.mongo.exception;

import com.mongodb.com.ty.mongo.entity.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseStructure> handleException(NoSuchElementException e){
        ResponseStructure responseStructure = new ResponseStructure();
        responseStructure.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        responseStructure.setHttpStatus(HttpStatus.BAD_REQUEST);
        responseStructure.setData(null);

        return new ResponseEntity<ResponseStructure>(responseStructure , HttpStatus.BAD_REQUEST);
    }
}
