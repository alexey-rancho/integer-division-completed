package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DivisionMathProviderTest {
    @Test
    void testProvide() {
        DivisionMathProvider divisionMathProvider = new DivisionMathProvider(78945, 4);
        DivisionMathResult divisionMathResult = divisionMathProvider.provide();

        String expected = "[DivisionStep{index=1, multiplier=1, digit=7, subtractedNumber=4, remainder=3}, DivisionStep{index=2, multiplier=9, digit=38, subtractedNumber=36, remainder=2}, DivisionStep{index=3, multiplier=7, digit=29, subtractedNumber=28, remainder=1}, DivisionStep{index=4, multiplier=3, digit=14, subtractedNumber=12, remainder=2}, DivisionStep{index=5, multiplier=6, digit=25, subtractedNumber=24, remainder=1}]";
        Assertions.assertEquals(expected, divisionMathResult.toString());
    }
}
