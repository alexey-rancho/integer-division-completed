package com.calculator.integerdivision.validator;

public class DivisionValidator {

    public void validateDigits(int dividend, int divider) {
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
