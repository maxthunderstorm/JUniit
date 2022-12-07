package com.example;

import com.example.data.UserRepository;
import com.example.model.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class UserServiceTest
{

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;


    @BeforeEach
    void init() {
        firstName = "firstName";
        lastName = "lastName";
        email = "test@example.com";
        password = "123456789";
        repeatPassword = "123456789";

    }
    @Test
    @DisplayName("User object created")
    void testCreateUser_whenUserDetailsProvided_returnUserObject()
    {
        //Given

        when( userRepository.save( any(User.class) ) ).thenReturn( true );

        //When
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);


        userRepository.save(user);

        //Then
        assertNotNull(user);
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId());

    }

}
