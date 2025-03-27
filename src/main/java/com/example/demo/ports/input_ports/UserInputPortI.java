package com.example.demo.ports.input_ports;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.example.demo.application.dtos.UserDto;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.entities.User;

public abstract class UserInputPortI {
    public abstract CompletableFuture<ResponseEntity<ArrayList<UserDto>>> getAllUsers();
    public  abstract  CompletableFuture<ResponseEntity<UserDto>> addUser(User user);
}
