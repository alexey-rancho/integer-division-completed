package com.calculator.integerdivision.domain.math;

import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.math.DivisionMathStep;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivisionMathResultTest {

    @Test
    void testGetStep() {
        DivisionMathResult result = new DivisionMathProvider().provide(24,6);
        Assertions.assertNull(
                result.getStep(1),
                "expected getStep() return null, but it didn't"
        );
        Assertions.assertEquals(
                DivisionMathStep.class,
                result.getStep(0).getClass(),
                "expected getStep() return DivisionMathStep object, but it didn't"
        );
    }

    @Test
    void testGetResultWhenGotResultIsCorrect() {
        DivisionMathProvider provider = new DivisionMathProvider();
        Assertions.assertEquals(4, provider.provide(24, 6).getResult());
    }

    @Test
    void testGetResultWhenMultiplierCannotBeParsedToInteger() {
        DivisionMathResult result2 = new DivisionMathResult(1, 1)
                .addStep(DivisionMathStep.newBuilder().setMultiplier(999999999).build())
                .addStep(DivisionMathStep.newBuilder().setMultiplier(1).build());
        String message2 = "expected getResult() return 0 as wrong result but it didn't";
        Assertions.assertEquals(0, result2.getResult(), message2);
    }

    @Test
    void testGetResultWhenMathResultHasNoSteps() {
        DivisionMathResult result = new DivisionMathResult(1, 1);
        String message = "expected getResult() return 0 as wrong result but it didn't";
        Assertions.assertEquals(0, result.getResult(), message);
    }

}
