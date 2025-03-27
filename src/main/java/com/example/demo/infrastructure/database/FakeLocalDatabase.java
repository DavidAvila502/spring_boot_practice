package com.example.demo.infrastructure.database;

import com.example.demo.domain.entities.User;

import java.util.ArrayList;
import java.util.Arrays;

public class FakeLocalDatabase {

    private static FakeLocalDatabase intance;

    public ArrayList<User> Users;

    private FakeLocalDatabase(){
        this.Users = new ArrayList<User>(Arrays.asList(
                new User(1,"David",25,null),
                new User(2,"Chato",5,"123"),
                new User(3,"Coqueta",1,"9090")
        ));

    }

    public static synchronized  FakeLocalDatabase getInstance(){
        if(intance == null){
            intance = new FakeLocalDatabase();
        }
        return  intance;
    }
}
