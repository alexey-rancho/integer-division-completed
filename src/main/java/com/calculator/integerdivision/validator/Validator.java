package com.calculator.integerdivision.validator;

public interface Validator {
    /**
     * @apiNote This method validates dividend and divider before division
     * */
    void validateDigits(int dividend, int divider);

}
