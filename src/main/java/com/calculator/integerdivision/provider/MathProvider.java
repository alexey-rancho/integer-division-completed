package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;

public interface MathProvider extends Provider<DivisionMathResult> {
    MathProvider setup(int dividend, int divider);
}
