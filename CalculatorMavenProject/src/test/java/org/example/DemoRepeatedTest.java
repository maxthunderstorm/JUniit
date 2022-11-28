package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {

    private Calculator calculator;

    @BeforeEach
    void beforeEachTestMethod() {
        System.out.println( "Executing @BeforeEach method. " );
        calculator = new Calculator();
    }

    @DisplayName( "Division by zero" )
   // @RepeatedTest(3)
            @RepeatedTest(value = 3, name = "{displayName}. Repition {currentRepetition} of" +
                    " {totalRepetitions}")
        //@Disabled( "TODO: still need to work on it " )
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException(
            RepetitionInfo repetitionInfo,
            TestInfo testInfo
    )
    {
        System.out.println( "Running " + testInfo.getTestMethod().get().getName());
        System.out.println("Repition # " + repetitionInfo.getCurrentRepetition() +
                " of " + repetitionInfo.getTotalRepetitions());
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
}
