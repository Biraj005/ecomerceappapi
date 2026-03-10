package com.biraj.ecomerceappapi.exceptions;

public class UnauthorizedException extends  RuntimeException{
    public  UnauthorizedException(String message){
        super(message);
    }
}
