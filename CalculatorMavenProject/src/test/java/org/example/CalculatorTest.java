package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Please add your description here.
 *
 * @author Pulse Innovations Ltd
 */

@DisplayName( "Test math operations in Calculator class" )
class CalculatorTest
{
    // test<System Under Test>_<Condition or State Change>_<Expected Result>
    @DisplayName( "Test 4/2 = 2" )
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo()
    {
        Calculator calculator = new Calculator();
        int result = calculator.integerDivision( 4, 2 );
        assertEquals( 2, result, "4/2 did not produce 2" );
    }

    @DisplayName( "Division by zero" )
    @Test
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException()
    {
        fail( "Not implemented yet" );
    }

    @DisplayName( "Test 33-1=32" )
    @Test
    void integerSubtraction()
    {
        Calculator calculator = new Calculator();
        int minuend = 33;
        int subtrahend = 1;
        int expectedResult = 32;

        int result = calculator.integerSubtraction( minuend, subtrahend );

        assertEquals( expectedResult, result,
            () -> minuend + "-" + subtrahend + " did not produce " + expectedResult
        );
    }
}