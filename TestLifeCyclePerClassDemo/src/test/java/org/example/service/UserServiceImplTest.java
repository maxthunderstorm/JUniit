package org.example.service;

import java.util.HashMap;
import java.util.Map;

import org.example.io.UsersDatabase;
import org.example.io.UsersDatabaseMapImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    private UsersDatabase usersDatabase;
    private UserService userService;

    private String createdUserId = "";

    @BeforeAll
    void setup() {
        // Create & initialize database
        usersDatabase = new UsersDatabaseMapImpl();
        usersDatabase.init();
        userService = new UserServiceImpl( usersDatabase );
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
        usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
        //Given
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put( "firstName", "Jan" );
        userDetails.put( "lastName", "lastName" );

        //When
        createdUserId = userService.createUser( userDetails );

        //Then
        assertNotNull(createdUserId, "User id should not be null.");
    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {

        //Given
        Map<String, String> newUserDetails = new HashMap<>();
        newUserDetails.put( "firstName", "John" );
        newUserDetails.put( "lastName", "Doe" );

        //When
        Map updatedUserDetails = userService.updateUser( createdUserId, newUserDetails );

        //Then
        assertEquals( newUserDetails.get( "firstName" ), updatedUserDetails.get( "firstName" ),
            "Returned value of user's first name is incorrect");

        assertEquals( newUserDetails.get( "lastName" ), updatedUserDetails.get( "lastName" ),
            "Returned value of user's last name is incorrect");
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

        //when
        Map userDetails = userService.getUserDetails( createdUserId );

        //then
        assertNotNull( userDetails, "User details should not be null" );
        assertEquals( createdUserId, userDetails.get( "userId" ), "Returned user details has incorrect user id" );
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {


        //When
        userService.deleteUser( createdUserId );

        //then
        assertNull( userService.getUserDetails( createdUserId ), "user should not been found" );

    }

}
