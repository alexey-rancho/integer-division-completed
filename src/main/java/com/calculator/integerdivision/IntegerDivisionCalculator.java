package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionValidator;

public class IntegerDivisionCalculator {

    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;
    private final DivisionValidator divisionValidator;

    public IntegerDivisionCalculator(DivisionMathProvider divisionMathProvider,
                                     DivisionViewProvider divisionViewProvider,
                                     DivisionValidator divisionValidator) {
        this.divisionMathProvider = divisionMathProvider;
        this.divisionViewProvider = divisionViewProvider;
        this.divisionValidator = divisionValidator;
    }

    public String calculateDivision(int dividend, int divider) {
        divisionValidator.validateDigits(dividend, divider);

        DivisionResult divisionResult = divisionMathProvider.provideMathDivision(dividend, divider);
        // result will be passed to view
        return divisionViewProvider.provideView(divisionResult);
    }

}
