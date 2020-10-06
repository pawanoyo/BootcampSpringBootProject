package com.example.demo.exception;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(){
        super("Customer is already exist");
    }

    public AlreadyExistException(String message){
        super(message);
    }
}
