package com.example.demo.infrastructure.exception;

import java.time.Instant;

public record ErrorMessage(
Instant timestamp,
String errorCode,
String message,
String details
) {
    public ErrorMessage(String errorCode, String message){
        this(Instant.now(),errorCode,message, null);
    }

    public ErrorMessage(String errorCode, String message,String details){
        this(Instant.now(),errorCode,message, details);
    }

}
