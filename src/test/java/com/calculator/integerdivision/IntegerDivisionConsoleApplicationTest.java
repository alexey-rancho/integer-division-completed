package com.calculator.integerdivision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

public class IntegerDivisionConsoleApplicationTest {

    @ParameterizedTest
    @ArgumentsSource(DivisionViewArgumentsProvider.class)
    void testDivisionViewWhenDividendBiggerThanDivider(int dividend, int divider, String expected) {
        IntegerDivisionCalculator integerDivisionCalculator = new IntegerDivisionCalculator(dividend, divider);
        String result = integerDivisionCalculator.getDivisionView().getResult();
        Assertions.assertEquals(expected, result);
    }
}
