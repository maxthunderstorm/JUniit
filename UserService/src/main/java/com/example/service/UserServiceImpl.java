package com.example.service;

import com.example.data.UserRepository;
import com.example.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

        User user = new User( firstName, lastName, email, UUID.randomUUID().toString() );
        boolean isUserCreated = userRepository.save( user );

        if (!isUserCreated) throw new UserServiceException("Could not create user!");

        return user;
    }
}
