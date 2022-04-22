package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.DivisionMathArgumentsProvider;
import com.calculator.integerdivision.domain.DivisionMathResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DivisionMathProviderTest {
    @ParameterizedTest
    @ArgumentsSource(DivisionMathArgumentsProvider.class)
    void testProvidedMathResult(DivisionMathResult expected) {
        DivisionMathResult actual = new DivisionMathProvider()
                .setup(expected.getDividend(), expected.getDivider())
                .provide();
        Assertions.assertEquals(expected, actual);
    }
}
