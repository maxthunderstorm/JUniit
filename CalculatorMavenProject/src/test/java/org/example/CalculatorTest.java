package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;



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
    //@Disabled( "TODO: still need to work on it " )
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException()
    {
        System.out.println( "Running division by zero ");
        // Given
        int dividend = 4;
        int divsior = 0;
        String expectedExceptionMessage = "/ by zero";

        ArithmeticException actualException = assertThrows(ArithmeticException.class, () ->
        {
            // When
            calculator.integerDivision(dividend, divsior);
        }, "Division by zero should throw an ArithmeticException");

        // Then
        assertEquals(expectedExceptionMessage, actualException.getMessage(),
                "Unexpected exception message");
    }

    @ParameterizedTest
    //works only with one parameter
    @ValueSource(strings = {"John", "Kate", "Alice"})
    void valueSourceDemonstration(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    @DisplayName( "Test integer subtraction [minuend, subtrahend, expectedResult]" )
    @ParameterizedTest
    //@MethodSource("integerSubtractionParameters") "" are needed if the name differ
    //@MethodSource()
//    @CsvSource( {
//            "33, 1, 32",
//            "24, 1, 23",
//            "54, 1, 53",
//    } )
//    @CsvSource({
//            "apple, orange",
//            "apple, ''",
//            "apple,", //second param is null
//    } )
    @CsvFileSource(resources = "/integerSubtraction.csv")
    void integerSubtraction(int minuend, int subtrahend, int expectedResult)
    {
        int result = calculator.integerSubtraction( minuend, subtrahend );

        assertEquals( expectedResult, result,
            () -> minuend + "-" + subtrahend + " did not produce " + expectedResult
        );
    }

//    private static Stream<Arguments> integerSubtraction() {
//        return Stream.of(
//                Arguments.of(33, 1, 32),
//                Arguments.of(54, 1, 53),
//                Arguments.of(24, 1, 23)
//        );
//    }
}