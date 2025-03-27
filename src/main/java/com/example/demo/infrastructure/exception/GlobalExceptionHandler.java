package com.example.demo.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.domain.entities.exceptions.UsersNotFoundException;
import java.util.concurrent.CompletionException;
import com.example.demo.infrastructure.exception.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UsersNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(ex.getErrorCode(),ex.getMessage()));
    }

    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<ErrorMessage> handleCompletionException(CompletionException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof UsersNotFoundException) {
            return handleUserNotFoundException((UsersNotFoundException) cause);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage("Error: 02","Internal server error"));
    }
}