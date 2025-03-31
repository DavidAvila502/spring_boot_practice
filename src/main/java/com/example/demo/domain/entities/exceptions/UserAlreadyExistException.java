package com.example.demo.domain.entities.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    private final String errorCode;

    public UserAlreadyExistException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }
}
