package com.example.demo.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import com.example.demo.application.dtos.UserDto;
import com.example.demo.infrastructure.database.FakeLocalDatabase;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.User;
import com.example.demo.ports.output_ports.UserRepository;

@Repository
public class UserRepositoryImp extends UserRepository{
    Random rand = new Random();

    @Override
    @Async("taskExecutor")
    public CompletableFuture<ArrayList<User>> getAllUsers() {
        // simulation Asynchronous
        try{
            Thread.sleep(rand.nextInt(5000 - 1000 + 1)+ 1000);

        }catch(InterruptedException e){

            Thread.currentThread().interrupt();
        }
        // Returning the answer
        return CompletableFuture.completedFuture(
                FakeLocalDatabase.getInstance().Users
        );
    }

    @Async("taskExecutor")
    public CompletableFuture<Optional<User>> getUserById(int Id) {
        // simulation Asynchronous
        try{
            Thread.sleep(rand.nextInt(5000 - 1000 + 1)+ 1000);

        }catch(InterruptedException e){

            Thread.currentThread().interrupt();
        }

        Optional<User> user =   FakeLocalDatabase.getInstance()
                .Users.stream()
                .filter(currentUser -> currentUser.getId() == Id)
                .findFirst();

        return CompletableFuture.completedFuture(user);

    }

    @Async("taskExecutor")
    public CompletableFuture<User> addNewUser(User user){
        try{
            Thread.sleep(rand.nextInt(5000 - 1000 + 1)+ 1000);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        FakeLocalDatabase.getInstance().Users.add(user);
        return  CompletableFuture.completedFuture(user);

    }

    @Async("taskExecutor")
    public CompletableFuture<Void> deleteUser(User user) {
        try{
            Thread.sleep(rand.nextInt(5000 - 1000 + 1)+ 1000);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        FakeLocalDatabase.getInstance().Users.remove(user);
        return CompletableFuture.completedFuture(null);
    }

    @Async("taskExecutor")
    public CompletableFuture<Optional<User>> updateUser(User user) {
        try{
            Thread.sleep(rand.nextInt(5000 - 1000 + 1)+ 1000);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

       Optional<User> userfound =  FakeLocalDatabase.getInstance()
               .Users
               .stream()
               .filter(u -> u.getId() == user.getId())
               .findFirst();

        userfound.ifPresent(u -> {
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setPassword(user.getPassword());

        });

        return  CompletableFuture.completedFuture(userfound);

    }
}
