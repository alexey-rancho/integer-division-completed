package com.calculator.integerdivision.validator;

public class DivisionNumberValidator implements NumberValidator {

    /**
     * Validates dividend and divider before division
     *
     * @param dividend must be > divider and > 0
     * @param divider  must be > 0
     * @throws IllegalArgumentException if dividend < divider, or dividend < 0,
     *                                  or divider < 0, or divider == 0
     */
    public void validate(int dividend, int divider) throws IllegalArgumentException {
        if (divider == 0) {
            throw new IllegalArgumentException("Division by zero cannot be done");
        }
        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend cannot be negative");
        }
        if (divider < 0) {
            throw new IllegalArgumentException("Divider cannot be negative");
        }
        if (dividend < divider) {
            throw new IllegalArgumentException("Divider cannot be > dividend");
        }
    }

}
