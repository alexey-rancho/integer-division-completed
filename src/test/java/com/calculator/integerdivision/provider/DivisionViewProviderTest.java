package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.DivisionViewArgumentsProvider;
import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DivisionViewProviderTest {

    @ParameterizedTest
    @ArgumentsSource(DivisionViewArgumentsProvider.class)
    void testProvideWhenDividendBiggerThanDivider(int dividend, int divider, DivisionViewResult expected) {
        DivisionMathResult mathResult = new DivisionMathResult(dividend, divider);
        DivisionViewResult actual = new DivisionViewProvider().provide(mathResult);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testProvideWhenNullPassedAsArgument() {
        DivisionViewProvider viewProvider = new DivisionViewProvider();
        NullPointerException exception = Assertions.assertThrowsExactly(
                NullPointerException.class,
                () -> viewProvider.provide(null),
                "Expected provide(null) to throw NullPointerException but it didn't"
        );
        String expectedMessage = "mathResult argument must not be null";
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}
