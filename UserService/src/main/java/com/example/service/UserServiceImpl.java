package com.example.service;

import com.example.data.UserRepository;
import com.example.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService
{

    private UserRepository userRepository;
    private EmailVerificationService emailVerificationService;

    public UserServiceImpl( UserRepository userRepository, EmailVerificationService emailVerificationService )
    {
        this.userRepository = userRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser( String firstName, String lastName, String email, String password, String repeatPassword )
    {

        User user = new User( firstName, lastName, email, UUID.randomUUID().toString() );
        boolean isUserCreated = false;

        try
        {
            isUserCreated = userRepository.save( user );
        }
        catch ( RuntimeException ex )
        {
            throw new UserServiceException( ex.getMessage() );
        }

        if ( !isUserCreated )
        {
            throw new UserServiceException( "Could not create user!" );
        }

        try
        {
            emailVerificationService.scheduleEmailConfirmation( user );
        }
        catch ( RuntimeException ex )
        {
            throw new EmailNotificationServiceException( ex.getMessage() );
        }

        return user;
    }

    void test()
    {
        System.out.println();
    }
}
