package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DivisionMathProviderTest {
    @Test
    void testProvide() {
        DivisionMathProvider divisionMathProvider = new DivisionMathProvider(78945, 4);
        DivisionMathResult divisionMathResult = divisionMathProvider.provide();
    }
}
