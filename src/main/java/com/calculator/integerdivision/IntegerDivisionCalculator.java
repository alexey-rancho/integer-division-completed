package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DigitValidator;

public class IntegerDivisionCalculator {

    private final DivisionMathProvider mathProvider;
    private final DivisionViewProvider viewProvider;
    private final DigitValidator digitValidator;

    public IntegerDivisionCalculator(DivisionMathProvider mathProvider,
                                     DivisionViewProvider viewProvider,
                                     DigitValidator validator) {
        this.digitValidator = validator;
        this.mathProvider = mathProvider;
        this.viewProvider = viewProvider;
    }

    public DivisionFinalResult calc(int dividend, int divider) {
        digitValidator.validate(dividend, divider);
        DivisionMathResult mathResult = mathProvider.provide(dividend, divider);
        DivisionViewResult viewResult = viewProvider.provide(mathResult);
        return new DivisionFinalResult(mathResult, viewResult);
    }

}
