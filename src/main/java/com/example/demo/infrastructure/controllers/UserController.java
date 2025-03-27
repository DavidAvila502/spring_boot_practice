package com.example.demo.infrastructure.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.example.demo.application.dtos.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.entities.User;
import com.example.demo.ports.input_ports.UserInputPortI;
import com.example.demo.ports.service_ports.UserServiceI;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/users")
public class UserController extends UserInputPortI{

    private final UserServiceI _userService;
    @Autowired
    public UserController(UserServiceI userService){
        _userService = userService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<ArrayList<UserDto>>> getAllUsers() {
     return _userService.getAllUsers()
     .thenApply(users ->{
        return ResponseEntity.ok(users);
     });
}

    @PostMapping
    public CompletableFuture<ResponseEntity<UserDto>> addUser(@RequestBody User user) {

        // Capture request information in the main thread
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        return  _userService.addNewUser(user).thenApply(userDto -> {

            // Build URI using captured request information
            URI location = UriComponentsBuilder.newInstance()
                    .scheme(request.getScheme())
                    .host(request.getServerName())
                    .port(request.getServerPort())
                    .path(request.getRequestURI())
                    .path("/{id}")
                    .buildAndExpand(userDto.getId())
                    .toUri();

            // Return the response to client
                    return ResponseEntity.created(location).body(userDto);
                }
        );
    }

}