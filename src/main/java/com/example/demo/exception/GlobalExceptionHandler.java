package com.example.demo.exception;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handelCustomerNotFoundException (CustomerNotFoundException exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date().toString(), HttpStatus.NOT_FOUND,exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> handelCustomerNotFoundException (BadRequest exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date().toString(), HttpStatus.BAD_REQUEST,exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handelGlobalException (Exception exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

