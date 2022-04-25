package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.NumberValidator;

public class IntegerDivisionCalculator {

    private final DivisionMathProvider mathProvider;
    private final DivisionViewProvider viewProvider;
    private final NumberValidator numberValidator;

    public IntegerDivisionCalculator(DivisionMathProvider mathProvider,
                                     DivisionViewProvider viewProvider,
                                     NumberValidator validator) {
        this.numberValidator = validator;
        this.mathProvider = mathProvider;
        this.viewProvider = viewProvider;
    }

    public DivisionFinalResult calc(int dividend, int divider) {
        numberValidator.validate(dividend, divider);
        DivisionMathResult mathResult = mathProvider.provide(dividend, divider);
        DivisionViewResult viewResult = viewProvider.provide(mathResult);
        return new DivisionFinalResult(mathResult, viewResult);
    }

}
