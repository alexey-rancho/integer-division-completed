package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.Result;
import com.calculator.integerdivision.domain.DivisionResult;
import com.calculator.integerdivision.provider.*;
import com.calculator.integerdivision.validator.DigitValidator;

public class IntegerDivisionCalculator {

    private final MathProvider mathProvider;
    private final ViewProvider viewProvider;
    private final DigitValidator digitValidator;

    public IntegerDivisionCalculator(MathProvider mathProvider, ViewProvider viewProvider, DigitValidator validator) {
        this.digitValidator = validator;
        this.mathProvider = mathProvider;
        this.viewProvider = viewProvider;
    }

    public DivisionResult calc(int dividend, int divider) {
        digitValidator.validate(dividend, divider);
        mathProvider.setup(dividend, divider);
        viewProvider.setup(mathProvider.provide());
        return new DivisionResult(mathProvider.provide(), viewProvider.provide());
    }

    public Result<String> getDivisionView() {
        return viewProvider.provide();
    }

    public Result<Integer> getDivisionMath() {
        return mathProvider.provide();
    }

}
