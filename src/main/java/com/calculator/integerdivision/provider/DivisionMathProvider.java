package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.domain.DivisionStep;


public class DivisionMathProvider implements MathProvider {
    private int dividend;
    private int divider;

    @Override
    public DivisionMathProvider setup(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
        return this;
    }

    @Override
    public DivisionMathResult provide() {
        return calcResult();
    }

    private DivisionMathResult calcResult() {
        DivisionMathResult mathResult = new DivisionMathResult(dividend, divider);

        int[] digits = splitNumber(dividend);
        int digit = 0;
        int remainder = 0;
        boolean isStepFinished = false;

        DivisionStep.Builder stepBuilder = DivisionStep.newBuilder();

        for (int index = 0; index < digits.length; index++) {
            if (isStepFinished) {
                stepBuilder = DivisionStep.newBuilder();
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

            boolean condition = isNextDigitNull(index, digits) || isNextDigitEqualOrLess(index, digits);
            if (isDividerPartOfDigit(divider, digit) && condition) {
                continue;
            }
            if (digit >= divider || digit == 0) {
                DivisionStep step = divide(stepBuilder, digit, divider);
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

    private DivisionStep divide(DivisionStep.Builder builder, int dividend, int divider) {
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

    private boolean isNextDigitEqualOrLess(int index, int[] digits) {
        return digits.length > 0
                && index >= 0
                && index < digits.length - 1
                && digits[index + 1] <= digits[index];
    }

    private int concatNumbers(int a, int b) {
        return Integer.parseInt(String.valueOf(a) + String.valueOf(b));
    }

    private int[] splitNumber(int number) {
        String[] stringNumbers = String.valueOf(number).split("");
        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }
        return numbers;
    }

}
