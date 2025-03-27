
package com.example.demo.application.services;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;


import com.example.demo.application.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.exceptions.UsersNotFoundException;
import com.example.demo.ports.output_ports.UserRepository;
import com.example.demo.ports.service_ports.UserServiceI;

@Service
public class UserService extends UserServiceI {

    private final UserRepository _userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        _userRepository = userRepository;
    }

    @Override
    @Async
    public CompletableFuture<ArrayList<UserDto>> getAllUsers() {
        return _userRepository.getAllUsers()
                .thenApply(users -> {
                    if (users.isEmpty()) {
                        throw new UsersNotFoundException("CODE: 01", "The user was not found");
                    }
                    return users.stream().map(UserDto::from).collect(Collectors.toCollection(ArrayList::new));

                }).exceptionally(ex ->{
                    throw  new CompletionException("Failed to fetch users",ex);
                });
    }

    @Override
    @Async
    public CompletableFuture<UserDto> addNewUser(User user) {
        return  _userRepository.addNewUser(user).thenApply(newUser -> {

            return UserDto.from(user);

        }).exceptionally(ex -> {
            throw  new CompletionException("Failed to create the user",ex);
        });
    }


}