package com.example.demo.domain.entities;

import java.util.Optional;

public class User {

    private int Id;
    private String name;
    private int age;
    private final String password;


    public User(int Id, String name, int age,String password){
        this.Id = Id;
        this.name = name;
        this.age= age;
        this.password = password;
    }

    //Getters
    public int getId(){return Id;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public String getPassword (){return password;}
}
