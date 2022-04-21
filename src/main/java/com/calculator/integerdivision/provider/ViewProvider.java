package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.domain.DivisionViewResult;

public interface ViewProvider extends Provider<DivisionViewResult> {
    void setup(DivisionMathResult mathResult);
}
