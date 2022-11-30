package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

//@TestInstance( TestInstance.Lifecycle.PER_METHOD ) Default behaviour
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class MethodOrderedByOrderIndexTest
{

    private StringBuilder stringBuilder = new StringBuilder("");

    @AfterEach
    void afterEach()
    {
        System.out.println("The state of instance object is: " + stringBuilder);
    }

    @Order( 1 )
    @Test
    void testD()
    {
        System.out.println( "Running Test D" );
        stringBuilder.append( "1" );
    }

    @Order( 2 )
    @Test
    void testA()
    {
        System.out.println( "Running Test A" );
        stringBuilder.append( "2" );
    }

    @Order( 3 )
    @Test
    void testC()
    {
        System.out.println( "Running Test C" );
        stringBuilder.append( "3" );
    }

    @Order( 4 )
    @Test
    void testB()
    {
        System.out.println( "Running Test B" );
        stringBuilder.append( "4" );
    }
}
