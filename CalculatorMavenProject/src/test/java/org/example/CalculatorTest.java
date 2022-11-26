package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Please add your description here.
 *
 * @author Pulse Innovations Ltd
 */

@DisplayName( "Test math operations in Calculator class" )
class CalculatorTest
{
    private Calculator calculator;

    // use e. g. for db setup
    @BeforeAll
    static void setup() {
        System.out.println( "Executing @BeforeAll method. " );
    }

    // use e. g. for clean up a db
    @AfterAll
    static void cleanup() {
        System.out.println( "Executing @AfterAll method. ");
    }

    // use e. g. for initialize objects
    @BeforeEach
    void beforeEachTestMethod() {
        System.out.println( "Executing @BeforeEach method. " );
        calculator = new Calculator();
    }

    @AfterEach
    void afterEachTestMethod() {
        System.out.println( "Executing @AfterEach method. " );
        calculator = new Calculator();
    }

    // test<System Under Test>_<Condition or State Change>_<Expected Result>
    @DisplayName( "Test 4/2 = 2" )
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo()
    {
        // Arrange // Given
        int dividend = 4;
        int divisor = 2;
        int expectedResult = 2;

        // Act // When
        int actualResult = calculator.integerDivision( dividend, divisor );

        // Assert // Then
        assertEquals( expectedResult, actualResult, "4/2 did not produce 2" );
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
        int minuend = 33;
        int subtrahend = 1;
        int expectedResult = 32;

        int result = calculator.integerSubtraction( minuend, subtrahend );

        assertEquals( expectedResult, result,
            () -> minuend + "-" + subtrahend + " did not produce " + expectedResult
        );
    }
}