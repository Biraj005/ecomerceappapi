package com.biraj.ecomerceappapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "error", "Product Not Found",
                        "message", ex.getMessage()
                ));
    }
    @ExceptionHandler(MissingFieldError.class)
    public  ResponseEntity<?> handleMissingFieldError(MissingFieldError ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "timestamp",LocalDate.now(),
                        "error","Missing field",
                        "message",ex.getMessage()
                )
        );


    }

    @ExceptionHandler(AlreadyExists.class)
    public  ResponseEntity<?> alreadyExits(AlreadyExists ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "timestamp",LocalDate.now(),
                        "error","Conflict",
                        "message",ex.getMessage()
                )
        );
    }
    @ExceptionHandler(InternalServerError.class)
    public  ResponseEntity<?> internalServerErro(InternalServerError ex){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of(
                        "timestamp",LocalDate.now(),
                        "error","Server error",
                        "message",ex.getMessage()
                )
        );
    }
}