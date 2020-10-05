package com.example.demo.exception;

public class HandleBadRequestException extends RuntimeException {

    public HandleBadRequestException(String message){
        super(message);
    }
}
