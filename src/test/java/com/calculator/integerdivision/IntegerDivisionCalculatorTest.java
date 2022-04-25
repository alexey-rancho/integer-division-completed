package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerDivisionCalculatorTest {

    @Test
    void testCalc() {
        DivisionFinalResult result = new IntegerDivisionCalculator(
                new DivisionMathProvider(),
                new DivisionViewProvider(),
                new DivisionNumberValidator()
        ).calc(93882, 33);
        DivisionMathResult mathResult = new DivisionMathProvider().provide(93882, 33);
        DivisionViewResult viewResult = new DivisionViewProvider().provide(mathResult);

        Assertions.assertEquals(mathResult, result.getMath());
        Assertions.assertEquals(viewResult, result.getView());
    }

}
