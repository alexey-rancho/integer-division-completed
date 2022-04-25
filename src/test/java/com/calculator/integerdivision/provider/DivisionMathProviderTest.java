package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.provider.arumentsproviders.DivisionMathArgumentsProvider;
import com.calculator.integerdivision.domain.math.DivisionMathResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DivisionMathProviderTest {

    @ParameterizedTest
    @ArgumentsSource(DivisionMathArgumentsProvider.class)
    void testProvideWhenDividendBiggerThanDivider(DivisionMathResult expected) {
        DivisionMathResult actual = new DivisionMathProvider()
                .provide(expected.getDividend(), expected.getDivider());
        Assertions.assertEquals(expected, actual);
    }

}
