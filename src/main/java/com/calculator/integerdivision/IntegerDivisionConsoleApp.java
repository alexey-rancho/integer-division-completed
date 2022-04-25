package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionNumberValidator;

public class IntegerDivisionConsoleApp {

    public static void main(String[] args) {
        IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(
                new DivisionMathProvider(),
                new DivisionViewProvider(),
                new DivisionNumberValidator()
        );
        DivisionFinalResult result = calculator.calc(78945, 4);
        System.out.println(result.getView());
        System.out.println(result.getMath());
    }

}
