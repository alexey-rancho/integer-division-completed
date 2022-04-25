package com.calculator.integerdivision.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivisionNumberValidatorTest {

    @Test
    void testValidateWhenDividerIsZero() {
        assertValidator(1, 0, "Division by zero cannot be done");
    }

    @Test
    void testValidateWhenDividerBiggerThanDividend() {
        assertValidator(1, 2, "Divider cannot be > dividend");
    }

    @Test
    void testValidateWhenDividendIsNegative() {
        assertValidator(-1, 1, "Dividend cannot be negative");
    }

    @Test
    void testValidateWhenDividerIsNegative() {
        assertValidator(1, -1, "Divider cannot be negative");
    }

    void assertValidator(int dividend, int divider, String expectedMessage) {
        DivisionNumberValidator validator = new DivisionNumberValidator();
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(dividend, divider)
        );
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }


}
