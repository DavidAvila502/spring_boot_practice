
package com.example.demo.application.services;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;


import com.example.demo.application.dtos.UserDto;
import com.example.demo.application.dtos.UserUpdateDto;
import com.example.demo.domain.entities.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.exceptions.UsersNotFoundException;
import com.example.demo.ports.output_ports.UserRepository;
import com.example.demo.ports.service_ports.UserServiceI;

@Service
public class UserServiceImp extends UserServiceI {

    private final UserRepository _userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository){
        _userRepository = userRepository;
    }

    @Override
    @Async
    public CompletableFuture<Optional<UserDto>> getUserById(int id) {
        return _userRepository.getUserById(id).thenApply(user -> {
            if(user.isEmpty()){
                throw new UsersNotFoundException("CODE: 01", "The user was not found");
            }
            return user.map(UserDto::from);
        }).exceptionally(ex ->{
            throw  new CompletionException("Failed to fetch users",ex);
        });

    }

    @Override
    @Async
    public CompletableFuture<ArrayList<UserDto>> getAllUsers() {
        return _userRepository.getAllUsers()
                .thenApply(users -> {
//                    if (users.isEmpty()) {
//                        throw new UsersNotFoundException("CODE: 02", "The user was not found");
//                    }
                    return users.stream().map(UserDto::from).collect(Collectors.toCollection(ArrayList::new));

                }).exceptionally(ex ->{
                    throw  new CompletionException("Failed to fetch users",ex);
                });
    }

    @Override
    @Async
    public CompletableFuture<UserDto> addNewUser(User user) {
        return  _userRepository.getUserById(user.getId())
                .thenCompose(existentUser ->{
                        if(existentUser.isPresent()){
                            throw  new UserAlreadyExistException("CODE:03","The user already exist.");
                        }
                        return _userRepository.addNewUser(user);
                }).thenApply(UserDto::from)
                .exceptionally(ex ->{
                    throw  new CompletionException("Failed to fetch users",ex);

                });
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteUser(int id) {
        return _userRepository.getUserById(id)
                .thenCompose(user ->{
                    if(user.isEmpty()){
                        throw  new UsersNotFoundException("CODE 01","The user was not found");
                    }
                    return _userRepository.deleteUser(user.get());
                }).exceptionally(ex -> {
                    throw  new CompletionException("Failed to fetch users",ex);

                });
    }

    @Override
    public CompletableFuture<UserDto> updateUser(int id, UserUpdateDto user) {

        return _userRepository.getUserById(id)
                .thenCompose(userFound ->{

                if(userFound.isEmpty()){
                    throw  new UsersNotFoundException("CODE 01","The user was not found");
                }

                if(userFound.get().getId() != user.getId()){
                    throw  new UsersNotFoundException("CODE 04","The update data has not coincidence with the user found");

                }

                User userUpdated = new User(id,user.getName(),user.getAge(),user.getPassword());

                return _userRepository.updateUser(userUpdated);

        }).thenApply(updatedUser ->{

            return UserDto.from(updatedUser.get());

        }).exceptionally(ex->{
            throw  new CompletionException("Failed to fetch users",ex);
        });

    }
}