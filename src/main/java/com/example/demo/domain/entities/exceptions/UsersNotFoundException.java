package com.example.demo.domain.entities.exceptions;

public class UsersNotFoundException extends RuntimeException {
    private final String errorCode;

    public UsersNotFoundException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }

}