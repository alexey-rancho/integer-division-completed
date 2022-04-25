package com.calculator.integerdivision.domain.view;

import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivisionViewResultTest {

    @Test
    void testGetStep() {
        DivisionMathResult mathResult = new DivisionMathProvider().provide(55, 11);
        DivisionViewResult viewResult = new DivisionViewProvider().provide(mathResult);

        Assertions.assertNull(
                viewResult.getStep(1),
                "expected getStep() return null, but it didn't"
        );
        Assertions.assertEquals(
                DivisionViewStep.class,
                viewResult.getStep(0).getClass(),
                "expected getStep() return DivisionViewStep object, but it didn't"
        );
    }

}
