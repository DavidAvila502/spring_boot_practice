package com.example.demo.application.dtos;


import com.example.demo.domain.entities.User;

public class UserDto {

    private int Id;
    private String name;
    private int age;

    public UserDto(int Id, String name, int age){
        this.Id = Id;
        this.name = name;
        this.age = age;
    }

    //Factory
    // Static factory method
    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),    // Ensure User has getId()
                user.getName(),  // Ensure User has getName()
                user.getAge()   // Ensure User has getAge()
        );
    }

    //Getters
    public int getId(){
        return  Id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return  age;
    }


}
