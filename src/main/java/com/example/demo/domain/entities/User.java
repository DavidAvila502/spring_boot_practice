package com.example.demo.domain.entities;

import java.util.Optional;

public class User {

    private int Id;
    private String name;
    private int age;
    private  String password;

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

    // Setters

    //    public void setId(int Id){
    //        this.Id = Id;
    //    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
