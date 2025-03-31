package com.example.demo.ports.service_ports;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.example.demo.application.dtos.UserDto;
import com.example.demo.application.dtos.UserUpdateDto;
import com.example.demo.domain.entities.User;

public abstract class UserServiceI {
    public abstract CompletableFuture<Optional<UserDto>> getUserById(int id);
    public abstract CompletableFuture<ArrayList<UserDto>> getAllUsers();
    public abstract CompletableFuture<UserDto> addNewUser(User user);
    public abstract  CompletableFuture<Void> deleteUser(int Id);
    public abstract  CompletableFuture<UserDto> updateUser(int id, UserUpdateDto user);
}
