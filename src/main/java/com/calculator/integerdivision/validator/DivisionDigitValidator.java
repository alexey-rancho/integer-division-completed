package com.calculator.integerdivision.validator;

public class DivisionDigitValidator implements DigitValidator {

    public void validate(int dividend, int divider) throws IllegalArgumentException {
        if (dividend < divider) {
            throw new IllegalArgumentException("Dividend bigger than divider is not supported");
        }
        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend can't be negative");
        }
        if (divider < 0) {
            throw new IllegalArgumentException("Divider can't be negative");
        }
        if (divider == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        }
    }

}
