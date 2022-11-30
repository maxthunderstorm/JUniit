package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserServiceTest
{
    @BeforeAll
    static void setup()
    {
        System.out.println( "Test methods related to user" );
    }

    @Test
    void testCreateUser_whenFirstNameIsMissing_throwsUserServiceException()
    {

    }
}
