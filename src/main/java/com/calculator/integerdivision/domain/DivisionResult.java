package com.calculator.integerdivision.domain;

public class DivisionResult {
    private final DivisionMathResult mathResult;
    private final DivisionViewResult viewResult;

    public DivisionResult(DivisionMathResult mathResult, DivisionViewResult viewResult) {
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
