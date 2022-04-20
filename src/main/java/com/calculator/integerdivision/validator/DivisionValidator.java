package com.calculator.integerdivision.validator;

public class DivisionValidator implements Validator {

    public void validateDigits(int dividend, int divider) throws IllegalArgumentException {
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
