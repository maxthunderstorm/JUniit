package com.example;

import com.example.model.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest
{
    @Test
    @DisplayName("User object created")
    void testCreateUser_whenUserDetailsProvided_returnUserObject()
    {
        //Given
        UserService userService = new UserServiceImpl();
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "test@example.com";
        String password = "123456789";
        String repeatPassword = "123456789";

        //When
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Then
        assertNotNull(user);
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");

    }

}
