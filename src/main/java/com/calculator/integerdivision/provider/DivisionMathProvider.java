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

            boolean condition1 = isDigitDividedWithNoRemainder(digit, divider) || isDividerPartOfDigit(divider, digit);
            boolean condition2 = isNextDigitNull(index, digits) || isNextDigitEqualOrLess(index, digits, divider);
            if (condition1 && condition2) {
                continue;
            }
            if (digit >= divider || digit == 0) {
                DivisionInfo divisionInfo = divide(digit, divider);
                DivisionMathStep step = buildMathStep(stepBuilder, divisionInfo);
                mathResult.addStep(step);

                isStepFinished = true;

                remainder = divisionInfo.remainder;
                if (remainder == 0) {
                    digit = 0;
                }
            }
        }

        return mathResult;
    }

    private DivisionInfo divide(int dividend, int divider) {
        int subNumber = dividend - (dividend % divider);
        int multiplier = subNumber / divider;
        int remainder = dividend - subNumber;

        return new DivisionInfo(subNumber, multiplier, remainder);
    }

    /**
     * Builds DivisionMathStep object by setting missing props
     * (multiplier, subNumber, remainder) which are retrieved
     * from DivisionInfo object
     *
     * @param stepBuilder  DivisionMathStep builder with already set digit
     * @param divisionInfo DivisionInfo instance got from divide() method
     * @return built DivisionMathStep instance
     */
    private DivisionMathStep buildMathStep(DivisionMathStep.Builder stepBuilder,
                                           DivisionInfo divisionInfo) {
        stepBuilder.setMultiplier(divisionInfo.multiplier)
                .setSubNumber(divisionInfo.subNumber)
                .setRemainder(divisionInfo.remainder);
        return stepBuilder.build();
    }

    private boolean isDigitDividedWithNoRemainder(int digit, int divider) {
        return digit % divider == 0;
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

    /**
     * Puts together passed numbers like that:
     * concatNumbers(1, 22, 3) -> 1223
     *
     * @return one concat integer from passed integers
     */
    private int concatNumbers(int... numbers) {
        String charNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .reduce("", (partial, next) -> partial + next);
        return Integer.parseInt(charNumbers);
    }

    private int[] splitNumber(int number) {
        return Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt).toArray();
    }

    /**
     * POD (Plain Old Data) class that's used for transferring division result
     * (dividend/divider -> subNumber, multiplier, remainder) between
     * divide and buildMathStep methods
     */
    private class DivisionInfo {
        private final int subNumber;
        private final int multiplier;
        private final int remainder;

        private DivisionInfo(int subNumber, int multiplier, int remainder) {
            this.subNumber = subNumber;
            this.multiplier = multiplier;
            this.remainder = remainder;
        }

    }

}
