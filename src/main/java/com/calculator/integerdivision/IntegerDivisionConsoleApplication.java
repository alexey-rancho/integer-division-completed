package com.calculator.integerdivision;

import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionValidator;

public class IntegerDivisionConsoleApplication {

    public static void main(String[] args) {
        IntegerDivisionCalculator integerDivisionCalculator = new IntegerDivisionCalculator(78945, 4);
        // 78945, 4
        System.out.println(integerDivisionCalculator.getDivisionView());
    }

}
