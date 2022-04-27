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

    /**
     * Calculates division using passed dividend and divider
     *
     * @return final result consists of math result and view result objects
     * that can be accessed
     */
    public DivisionFinalResult calc(int dividend, int divider) throws IllegalArgumentException {
        validate(dividend, divider);
        DivisionMathResult mathResult = mathProvider.provide(dividend, divider);
        DivisionViewResult viewResult = viewProvider.provide(mathResult);
        return new DivisionFinalResult(mathResult, viewResult);
    }

    private void validate(int dividend, int divider) throws IllegalArgumentException {
        numberValidator.validate(dividend, divider);
    }

}
