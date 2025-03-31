package com.example.demo.infrastructure.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import com.example.demo.application.dtos.UserDto;
import com.example.demo.application.dtos.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.entities.User;
import com.example.demo.ports.input_ports.UserInputPortI;
import com.example.demo.ports.service_ports.UserServiceI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/users")
public class UserController extends UserInputPortI{

    private final UserServiceI _userService;

    @Autowired
    public UserController(UserServiceI userService){
        this._userService = userService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<ArrayList<UserDto>>> getAllUsers() {
         return _userService.getAllUsers()
         .thenApply(users ->{
            return ResponseEntity.ok(users);
         });
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<UserDto>> getUserById(@PathVariable int id) {
        return  _userService.getUserById(id).thenApply(u->{
            return ResponseEntity.ok().body(u.get());
        });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<UserDto>> addUser(@RequestBody User user) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{Id}")
                .buildAndExpand(user.getId())
                .toUri();

        return _userService.addNewUser(user).thenApply(userDto -> {
            return ResponseEntity.created(location).body(userDto);
        });
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<UserDto>> updateUser(@PathVariable int id,@RequestBody UserUpdateDto user) {
       return  _userService.updateUser(id,user)
               .thenApply(userDto -> ResponseEntity.ok()
                       .body(userDto));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteUser(@PathVariable int id) {
        return _userService.deleteUser(id).thenApply(unused -> {
            return ResponseEntity.noContent().build();
        });
    }

}