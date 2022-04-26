package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.math.DivisionMathStep;

import java.util.Arrays;

public class DivisionMathProvider implements Provider {

    public DivisionMathProvider() {

    }

    public DivisionMathResult provide(int dividend, int divider) {
        return createResult(dividend, divider);
    }

    private DivisionMathResult createResult(int dividend, int divider) {
        DivisionMathResult mathResult = new DivisionMathResult(dividend, divider);

        int[] digits = splitNumber(dividend);
        int digit = 0;
        int remainder = 0;
        boolean isStepFinished = false;

        DivisionMathStep.Builder stepBuilder = DivisionMathStep.newBuilder();

        for (int index = 0; index < digits.length; index++) {
            if (isStepFinished) {
                stepBuilder = DivisionMathStep.newBuilder();
                isStepFinished = false;
            }

            if (remainder > 0) {
                digit = concatNumbers(remainder, digits[index]);
                stepBuilder.setDigit(digit);
            }
            if (digit > 0 && remainder == 0) {
                digit = concatNumbers(digit, digits[index]);
                stepBuilder.setDigit(digit);
            }
            if (digit == 0 && remainder == 0) {
                digit = digits[index];
                stepBuilder.setDigit(digit);
            }

            boolean condition = isNextDigitNull(index, digits) || isNextDigitEqualOrLess(index, digits, divider);
            if (isDividerPartOfDigit(divider, digit) && condition) {
                continue;
            }
            if (digit >= divider || digit == 0) {
                DivisionMathStep step = divide(stepBuilder, digit, divider);
                mathResult.addStep(step);
                isStepFinished = true;

                remainder = step.getRemainder();
                if (remainder == 0) {
                    digit = 0;
                }
            }
        }

        return mathResult;
    }

    private DivisionMathStep divide(DivisionMathStep.Builder builder, int dividend, int divider) {
        int subNumber = dividend - (dividend % divider);
        int multiplier = subNumber / divider;
        int remainder = dividend - subNumber;

        builder.setMultiplier(multiplier)
                .setSubNumber(subNumber)
                .setRemainder(remainder);

        return builder.build();
    }

    private boolean isDividerPartOfDigit(int divider, int digit) {
        return String.valueOf(digit).startsWith(String.valueOf(divider));
    }

    private boolean isNextDigitNull(int index, int[] digits) {
        return digits.length > 0
                && index >= 0
                && index < digits.length - 1
                && digits[index + 1] == 0;
    }

    private boolean isNextDigitEqualOrLess(int index, int[] digits, int divider) {
        return digits.length > 0
                && index >= 0
                && index < digits.length - 1
                && digits[index + 1] <= divider;
    }

    private int concatNumbers(int a, int b) {
        return Integer.parseInt("%d%d".formatted(a, b));
    }

    private int[] splitNumber(int number) {
        return Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt).toArray();
    }

}
