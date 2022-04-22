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
        DivisionResult result1 = calculator.calc(1111111, 111);
        System.out.println(result1.getView());
        System.out.println(result1.getMath());
    }

}
