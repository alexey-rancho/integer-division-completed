package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.math.DivisionMathResult;
import com.calculator.integerdivision.domain.math.DivisionMathStep;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import com.calculator.integerdivision.domain.view.DivisionViewStep;

public class DivisionViewProvider implements Provider {

    private static final String PIPE_LINE = "|";
    private static final String MID_LINE = "-";
    private static final String DOWN_LINE = "_";
    private static final String SPACE = " ";
    private static final String LF = "\n";

    public DivisionViewProvider() {

    }

    public DivisionViewResult provide(DivisionMathResult mathResult) {
        checkMathResult(mathResult);
        return createResult(mathResult);
    }

    private DivisionViewResult createResult(DivisionMathResult mathResult) {
        DivisionViewResult viewResult = new DivisionViewResult();
        String digitLeftSpaces = "";

        for (int index = 0; index < mathResult.size(); index++) {
            DivisionViewStep.Builder viewStepBuilder = DivisionViewStep.newBuilder();

            if (index == 0) {
                // dividend | divider part
                viewStepBuilder.addViewLine(
                        getHeaderLine(mathResult)
                );
            } else {
                // digit part
                digitLeftSpaces = getDigitLeftSpaces(mathResult, index, digitLeftSpaces);
                viewStepBuilder.addViewLine(
                        getDigitLine(mathResult, index, digitLeftSpaces)
                );
            }
            // subtracted number and delimiter part
            String subNumLeftSpaces = getSubNumLeftSpaces(mathResult, index, digitLeftSpaces);

            viewStepBuilder.addViewLine(
                    getSubNumberLine(mathResult, index, subNumLeftSpaces)
            );
            viewStepBuilder.addViewLine(
                    getDelimiterLine(mathResult, index, subNumLeftSpaces)
            );

            viewResult.addStep(viewStepBuilder.build());
        }

        return viewResult;
    }

    private void checkMathResult(DivisionMathResult mathResult) {
        if (mathResult == null) {
            throw new NullPointerException("mathResult argument must not be null");
        }
    }

    /**
     * @return division header line
     */
    private String getHeaderLine(DivisionMathResult mathResult) {
        return new StringBuilder()
                .append(DOWN_LINE)
                .append(mathResult.getDividend())
                .append(PIPE_LINE)
                .append(mathResult.getDivider())
                .append(LF)
                .toString();
    }

    /**
     * @return digit line after delimiter line
     */
    private String getDigitLine(DivisionMathResult mathResult,
                                int stepIndex,
                                String digitLeftSpaces) {
        DivisionMathStep mathStep = mathResult.getStep(stepIndex);
        return new StringBuilder()
                .append(digitLeftSpaces)
                .append(DOWN_LINE)
                .append(mathStep.getDigit())
                .append(getDigitRightSpaces(digitLeftSpaces, mathResult.getDividend(), mathStep.getDigit()))
                .append(PIPE_LINE)
                .append(LF)
                .toString();
    }

    /**
     * @return subtracted number line after digit line or header line
     */
    private String getSubNumberLine(DivisionMathResult mathResult,
                                    int stepIndex,
                                    String subNumLeftSpaces) {
        DivisionMathStep mathStep = mathResult.getStep(stepIndex);
        return new StringBuilder()
                .append(subNumLeftSpaces)
                .append(mathStep.getSubNumber())
                .append(getSubNumRightSpaces(subNumLeftSpaces, mathResult.getDividend(), mathStep.getSubNumber()))
                .append(PIPE_LINE)
                // if step == 0, delimiter between divider and result sets
                .append(stepIndex == 0 ? getResultDelimiter(mathResult.getResult()) : "")
                .append(LF)
                .toString();
    }

    /**
     * @return delimiter line after subtracted number line
     */
    private String getDelimiterLine(DivisionMathResult mathResult,
                                    int stepIndex,
                                    String subNumLeftSpaces) {
        return new StringBuilder()
                .append(subNumLeftSpaces)
                .append(getStepDelimiter(subNumLeftSpaces, mathResult.getDividend()))
                .append(PIPE_LINE)
                // if step == 0, result sets after step delimiter
                .append(stepIndex == 0 ? mathResult.getResult() : "")
                .append(LF)
                .toString();
    }

    private String getSubNumLeftSpaces(DivisionMathResult mathResult,
                                       int stepIndex,
                                       String digitLeftSpaces) {
        DivisionMathStep mathStep = mathResult.getStep(stepIndex);
        int digit = mathStep.getDigit();
        int subNumber = mathStep.getSubNumber();
        int lengthDiff = calcNumberLength(digit) - calcNumberLength(subNumber);
        return SPACE.repeat(1 + digitLeftSpaces.length() + lengthDiff);
    }

    private String getDigitLeftSpaces(DivisionMathResult mathResult,
                                      int index,
                                      String prevDigitLeftSpaces) {
        DivisionMathStep mathStep = mathResult.getStep(index - 1);
        if (mathStep == null) {
            return "";
        }

        int remainderLength = mathStep.getRemainder() > 0
                ? calcNumberLength(mathStep.getRemainder())
                : 0;
        int subNumberLength = mathStep.getRemainder() > 0
                ? calcNumberLength(mathStep.getRemainder())
                : 0;
        int digitLength = mathStep.getDigit() > 0
                ? calcNumberLength(mathStep.getDigit())
                : 0;

        if (remainderLength == 0) {
            return SPACE.repeat(prevDigitLeftSpaces.length() + subNumberLength);
        }
        return SPACE.repeat(
                prevDigitLeftSpaces.length()
                        + (digitLength - subNumberLength)
                        + (subNumberLength - remainderLength)
        );
    }

    private String getSubNumRightSpaces(String leftSpaces, int dividend, int subNumber) {
        return getRightSpaces(leftSpaces.length() - 1, dividend, subNumber);

    }

    private String getDigitRightSpaces(String leftSpaces, int dividend, int digit) {
        return getRightSpaces(leftSpaces.length(), dividend, digit);
    }

    private String getRightSpaces(int leftSpacesLength, int dividend, int digitOrSubNumber) {
        int lengthDiff = calcNumberLength(dividend) - calcNumberLength(digitOrSubNumber);
        int rightSpacesLength = lengthDiff - leftSpacesLength;
        return SPACE.repeat(rightSpacesLength);
    }

    private String getResultDelimiter(int result) {
        return MID_LINE.repeat(calcNumberLength(result));
    }

    private String getStepDelimiter(String subNumberLeftSpaces, int dividend) {
        return MID_LINE.repeat(calcNumberLength(dividend) - (subNumberLeftSpaces.length() - 1));
    }

    private int calcNumberLength(int number) {
        return String.valueOf(number).length();
    }

}
