package com.example.demo.application.dtos;

public class UserUpdateDto
{
    private int id;
    private int age;
    private String Name;
    private String password;

    public UserUpdateDto(int id,int age, String Name, String password){
        this.id =id;
        this.age =age;
        this.Name = Name;
        this.password =password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return password;
    }
}
