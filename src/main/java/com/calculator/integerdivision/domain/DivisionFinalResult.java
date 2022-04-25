package com.calculator.integerdivision.domain;

import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;

public class DivisionFinalResult {

    private final DivisionMathResult mathResult;
    private final DivisionViewResult viewResult;

    public DivisionFinalResult(DivisionMathResult mathResult, DivisionViewResult viewResult) {
        this.mathResult = mathResult;
        this.viewResult = viewResult;
    }

    public DivisionMathResult getMath() {
        return mathResult;
    }

    public DivisionViewResult getView() {
        return viewResult;
    }

}
