package com.biraj.ecomerceappapi.exceptions;

public class MissingFieldError extends  RuntimeException{
    public  MissingFieldError(String message){
        super(message);
    }
}
