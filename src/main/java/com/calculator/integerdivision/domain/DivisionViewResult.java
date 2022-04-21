package com.calculator.integerdivision.domain;

public class DivisionViewResult implements Result<String> {
    private final String view;

    public DivisionViewResult(String view) {
        this.view = view;
    }

    @Override
    public String getResult() {
        return view;
    }

    @Override
    public String toString() {
        return view;
    }
}
