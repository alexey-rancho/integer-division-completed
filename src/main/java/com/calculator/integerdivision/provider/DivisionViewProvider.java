package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.domain.DivisionMathStep;
import com.calculator.integerdivision.domain.DivisionViewResult;
import com.calculator.integerdivision.domain.DivisionViewStep;

import java.util.List;

public class DivisionViewProvider implements ViewProvider {

    private static final String PIPE_DELIMITER = "|";
    private static final String MIDDLE_DELIMITER = "-";
    private static final String DOWN_DELIMITER = "_";
    private static final String SPACE_DELIMITER = " ";
    private static final String LINE_BREAK = "\n";

    private DivisionMathResult mathResult;

    public DivisionViewProvider() {

    }

    @Override
    public void setup(DivisionMathResult mathResult) {
        this.mathResult = mathResult;
    }

    public DivisionViewResult provide() {
        DivisionViewResult viewResult = new DivisionViewResult();

        for (DivisionStep divisionStep : steps) {
            int stepIndex = steps.indexOf(divisionStep);
            int lastStepIndex = steps.size() - 1;

            int dividend = mathResult.getDividend();
            int divider = mathResult.getDivider();
            int digit = divisionStep.getDigit();
            int subtractedNumber = divisionStep.getSubtractedNumber();
            int remainder = divisionStep.getRemainder();

            leftSpaces.append(getLeftSpaces(mathResult, divisionStep, stepIndex));

            if (stepIndex == 0) {
                String rightSpaces = getRightSpaces(dividend, subtractedNumber);
                view.append(DOWN_DELIMITER)
                        .append(dividend)
                        .append(PIPE_DELIMITER)
                        .append(divider)
                        .append(LINE_BREAK)
                        .append(leftSpaces)
                        .append(subtractedNumber)
                        .append(rightSpaces)
                        .append(PIPE_DELIMITER)
                        .append(getMiddleDelimiter(quotient))
                        .append(LINE_BREAK)
                        .append(leftSpaces)
                        .append(getMiddleDelimiter(subtractedNumber))
                        .append(rightSpaces)
                        .append(PIPE_DELIMITER)
                        .append(quotient)
                        .append(LINE_BREAK);
            }
            if (stepIndex >= 1) {
                String addLeftSpaces = getAddLeftSpaces(subtractedNumber, digit);
                view.append(addMinusToSpaces(leftSpaces.toString()))
                        .append(digit)
                        .append(LINE_BREAK)
                        .append(addLeftSpaces)
                        .append(leftSpaces)
                        .append(subtractedNumber)
                        .append(LINE_BREAK)
                        .append(addLeftSpaces)
                        .append(leftSpaces)
                        .append(getMiddleDelimiter(subtractedNumber))
                        .append(LINE_BREAK);
            }

            if (stepIndex == lastStepIndex && remainder > 0) {
                view.append(leftSpaces).append(remainder);
            }
        }

        return viewResult;
    }

    private int calcQuotient(List<DivisionStep> steps) {
        StringBuilder quotientBuilder = new StringBuilder();

        for (DivisionStep divisionStep : steps) {
            quotientBuilder.append(divisionStep.getMultiplier());
        }

        return Integer.parseInt(quotientBuilder.toString());
    }

    private String addMinusToSpaces(String leftSpaces) {
        StringBuilder spacesAndMinus = new StringBuilder();
        char[] spaces = leftSpaces.toCharArray();

        spacesAndMinus.append(spaces);
        spacesAndMinus.replace(0, 1, DOWN_DELIMITER);
        spacesAndMinus.reverse();

        return spacesAndMinus.toString();
    }

    private String getAddLeftSpaces(int subtractedNumber, int digit) {
        StringBuilder addLeftSpaces = new StringBuilder();

        if (subtractedNumber < digit) {
            int difference = calcNumberLength(digit) - calcNumberLength(subtractedNumber);
            for (int i = 0; i < difference; i++) {
                addLeftSpaces.append(SPACE_DELIMITER);
            }
        }

        return addLeftSpaces.toString();
    }

    private String getLeftSpaces(DivisionMathResult mathResult, DivisionMathStep step, int stepIndex) {
        StringBuilder leftSpaces = new StringBuilder();

        int currentRemainder = step.getRemainder();
        int subtractedNumberLength = calcNumberLength(step.getSubNumber());
        int digitLength = calcNumberLength(step.getDigit());

        if (stepIndex == 0 && currentRemainder > 0) {
            int difference = digitLength - subtractedNumberLength;
            for (int i = 0; i < difference; i++) {
                leftSpaces.append(SPACE_DELIMITER);
            }
        }

        if (stepIndex > 0) {
            DivisionMathStep prevStep = mathResult.getSteps().get(stepIndex - 1);
            int prevDigit = prevStep.getDigit();
            int prevRemainder = prevStep.getRemainder();

            int difference = calcNumberLength(prevDigit) - calcNumberLength(prevRemainder);
            if (prevRemainder == 0) {
                for (int i = 0; i < calcNumberLength(prevDigit); i++) {
                    leftSpaces.append(SPACE_DELIMITER);
                }
            }
            if (prevRemainder > 0) {
                for (int i = 0; i < difference; i++) {
                    leftSpaces.append(SPACE_DELIMITER);
                }
            }

        }

        return leftSpaces.toString();
    }

    private String getRightSpaces(int dividend, int subtractedNumber) {
        StringBuilder spaces = new StringBuilder();
        int dividendLength = calcNumberLength(dividend);
        int subtractedNumberLength = calcNumberLength(subtractedNumber);
        int difference = dividendLength - subtractedNumberLength;

        for (int counter = 0; counter < difference; counter++) {
            spaces.append(SPACE_DELIMITER);
        }

        return spaces.toString();
    }

    private String getMiddleDelimiter(int number) {
        StringBuilder middleDelimiterBuilder = new StringBuilder();
        int numberLength = calcNumberLength(number);

        for (int counter = 0; counter < numberLength; counter++) {
            middleDelimiterBuilder.append(MIDDLE_DELIMITER);
        }

        return middleDelimiterBuilder.toString();
    }

    private int calcNumberLength(int number) {
        return String.valueOf(number).length();
    }

}
