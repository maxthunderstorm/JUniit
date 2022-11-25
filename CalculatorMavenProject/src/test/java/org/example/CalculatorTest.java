package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Please add your description here.
 *
 * @author Pulse Innovations Ltd
 */
class CalculatorTest
{
    @Test
    void integerDivsion()
    {
        Calculator calculator = new Calculator();
        int result = calculator.integerDivision( 4, 2 );
        assertEquals( 2, result, "4/2 did not produce 2" );
    }

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