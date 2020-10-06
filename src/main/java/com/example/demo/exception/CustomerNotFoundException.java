package com.example.demo.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(){
        super("Customer not found");
    }
    public CustomerNotFoundException(String message){
        super(message);
    }

}
