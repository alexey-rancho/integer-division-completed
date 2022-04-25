package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.provider.arumentsproviders.DivisionViewArgumentsProvider;
import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DivisionViewProviderTest {

    @ParameterizedTest
    @ArgumentsSource(DivisionViewArgumentsProvider.class)
    void testProvideWhenDividendBiggerThanDividerAndMathResultIsNotEmpty(int dividend, int divider, DivisionViewResult expected) {
        DivisionMathResult mathResult = new DivisionMathProvider().provide(dividend, divider);
        DivisionViewResult actual = new DivisionViewProvider().provide(mathResult);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testProvideWhenMathResultIsNull() {
        DivisionViewProvider viewProvider = new DivisionViewProvider();
        NullPointerException exception = Assertions.assertThrowsExactly(
                NullPointerException.class,
                () -> viewProvider.provide(null),
                "Expected provide(null) to throw NullPointerException but it didn't"
        );
        String expectedMessage = "mathResult argument must not be null";
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testProvideWhenMathResultIsEmpty() {
        DivisionViewProvider viewProvider = new DivisionViewProvider();
        DivisionMathResult mathResult = new DivisionMathResult(123, 2);

        DivisionViewResult expectedViewResult = new DivisionViewResult();
        DivisionViewResult actualViewResult = viewProvider.provide(mathResult);

        String failMessage = "expected provided DivisionViewResult to be empty, but it wasn't";
        Assertions.assertEquals(expectedViewResult, actualViewResult, failMessage);
    }

}
