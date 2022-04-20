package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionResult;
import com.calculator.integerdivision.domain.DivisionViewResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionValidator;

public class IntegerDivisionCalculator {

    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;
    private final DivisionValidator divisionValidator;

    public IntegerDivisionCalculator(int dividend, int divider) {
        this.divisionValidator = new DivisionValidator();
        divisionValidator.validateDigits(dividend, divider);

        this.divisionMathProvider = new DivisionMathProvider(dividend, divider);
        this.divisionViewProvider = new DivisionViewProvider(this.divisionMathProvider.provide());
    }

    public DivisionResult<String> getDivisionView() {
        return divisionViewProvider.provide();
    }

    public DivisionResult<Integer> getDivisionMath() {
        return divisionMathProvider.provide();
    }

}
