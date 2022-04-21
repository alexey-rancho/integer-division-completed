package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionDigitValidator;

public class IntegerDivisionConsoleApplication {
    public static void main(String[] args) {
        IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(
                new DivisionMathProvider(),
                new DivisionViewProvider(),
                new DivisionDigitValidator()
        );
        // 78945, 4 | 10002, 4
        DivisionResult result = calculator.calc(1230, 33);
        System.out.println(result.getView());
        System.out.println(result.getMath());
    }

}
