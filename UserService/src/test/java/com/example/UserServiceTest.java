package com.example;

import com.example.data.UserRepository;
import com.example.model.User;
import com.example.service.*;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
public class UserServiceTest
{

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailVerificationService emailVerificationService;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;


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

        //Then
        assertNotNull(user);
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId());
        Mockito.verify( userRepository, Mockito.times( 1 ) ).save( Mockito.any(User.class) );
        //Mockito.atLeast( 1 )
    }

    @DisplayName( "If save() method causes RuntimeException, a UserServiceException is thrown" )
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException()
    {
        //given
        when( userRepository.save( any(User.class) ) ).thenThrow( RuntimeException.class );

        //when
        assertThrows( UserServiceException.class, () -> {
            userService.createUser( firstName, lastName, email, password, repeatPassword );
        }, "Should have thrown UserServiceException instead" );


        //then
    }

    @Test
    @DisplayName("EmailNotificationException is handled")
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        //given

        //when
        when(userRepository.save(any(User.class))).thenReturn(true);
        doThrow(EmailNotificationServiceException.class)
                .when(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

        //then
        assertThrows( EmailNotificationServiceException.class, () -> {
            userService.createUser( firstName, lastName, email, password, repeatPassword );
        }, "Should have thrown UserServiceException instead" );

        verify(emailVerificationService, times(1))
                .scheduleEmailConfirmation(any(User.class));
    }

}
