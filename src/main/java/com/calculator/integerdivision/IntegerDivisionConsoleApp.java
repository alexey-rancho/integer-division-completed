package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionDigitValidator;

public class IntegerDivisionConsoleApp {

    public static void main(String[] args) {
        IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(
                new DivisionMathProvider(),
                new DivisionViewProvider(),
                new DivisionDigitValidator()
        );
        // 1000 / 2
        DivisionFinalResult result = calculator.calc(2040, 20);
        System.out.println(result.getView());
        System.out.println(result.getMath());
    }

}
