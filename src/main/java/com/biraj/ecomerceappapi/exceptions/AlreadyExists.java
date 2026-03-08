package com.biraj.ecomerceappapi.exceptions;

public class AlreadyExists extends  RuntimeException{
    public  AlreadyExists(String message){
        super(message);
    }
}
