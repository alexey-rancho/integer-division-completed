package com.calculator.integerdivision;

import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerDivisionConsoleApplicationTest {
    @Test
    void testDivision() {
        IntegerDivisionCalculator integerDivisionCalculator = new IntegerDivisionCalculator(
                new DivisionMathProvider(),
                new DivisionViewProvider(),
                new DivisionValidator()
        );
        // 78945, 4
        String result = integerDivisionCalculator.calculateDivision(78945, 4);
        String expected = "_78945|4\n" +
                " 4    |-----\n" +
                " -    |19736\n" +
                "_38\n" +
                " 36\n" +
                " --\n" +
                " _29\n" +
                "  28\n" +
                "  --\n" +
                "  _14\n" +
                "   12\n" +
                "   --\n" +
                "   _25\n" +
                "    24\n" +
                "    --\n" +
                "    1";
        Assertions.assertEquals(expected, result);
    }
}
