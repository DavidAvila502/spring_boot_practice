package com.example.demo.ports.output_ports;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.example.demo.application.dtos.UserDto;
import com.example.demo.application.dtos.UserInsertDto;
import com.example.demo.domain.entities.User;

public abstract class UserRepository {
    public abstract CompletableFuture<ArrayList<User>> getAllUsers();
    public abstract  CompletableFuture<Optional<User>> getUserById(int Id);
    public abstract  CompletableFuture<User> addNewUser(User user);
    public abstract CompletableFuture<Void> deleteUser(User user);
    public abstract  CompletableFuture<Optional<User>> updateUser(User user);
}
