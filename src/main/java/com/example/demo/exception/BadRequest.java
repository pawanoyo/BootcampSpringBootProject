package com.example.demo.exception;

public class BadRequest extends RuntimeException{

    public BadRequest(){
        super("Bad request");
    }

    public BadRequest(String message){

        super(message);
    }
}
