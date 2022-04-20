package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.domain.DivisionStep;

public class DivisionMathProvider implements Provider<DivisionMathResult> {
    private final int dividend;
    private final int divider;

    public DivisionMathProvider(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    @Override
    public DivisionMathResult provide() {
        DivisionMathResult divisionResult = new DivisionMathResult(dividend, divider);
        int[] digits = splitNumber(dividend);
        int remainder = 0;
        DivisionStep.Builder stepBuilder = DivisionStep.newBuilder();
        boolean isStepFinished = false;

        for (int index = 0; index < digits.length; index++) {
            if (isStepFinished) {
                stepBuilder = DivisionStep.newBuilder();
            }
            stepBuilder.setIndex(index);

            int digit = digits[index];

            if (remainder > 0) {
                digit = concatNumbers(remainder, digit);
                stepBuilder.setDigit(digit);
            } else {
                stepBuilder.setDigit(digit);
            }

            if (digit > divider || digit == 0) {
                divideNumbers(stepBuilder);
                remainder = stepBuilder.getRemainder();

                divisionResult.addDivisionStep(stepBuilder.build());
                isStepFinished = true;
            }
        }

        return divisionResult;
    }

    private int concatNumbers(int a, int b) {
        return Integer.parseInt(String.valueOf(a) + String.valueOf(b));
    }

    private int[] splitNumber(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        int[] numbers = new int[chars.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) chars[i];
        }
        return numbers;
    }

//    private StringBuilder getNumberAfterDecimal(StringBuilder digitBuilder) {
//    	if (currentIndex == lastIndex) {
//    		return digitBuilder;
//    	}
//
//    	currentIndex++;
//    	int number = getNumberFromDividend();
//    	if (number < divider) {
//    		StringBuilder newDigitBuilder = new StringBuilder(digitBuilder);
//    		newDigitBuilder.append(number);
//    		return newDigitBuilder;
//    	} else {
//    		currentIndex--;
//    		return digitBuilder;
//    	}
//    }
//
//    private StringBuilder getZeroAfterDigit() {
//        StringBuilder digitBuilder = new StringBuilder();
//
//        while (currentIndex < lastIndex) {
//            currentIndex++;
//            int guessNumber = getNumberFromDividend();
//            if (guessNumber == 0) {
//                digitBuilder.append(guessNumber);
//            } else {
//                currentIndex--;
//                break;
//            }
//        }
//
//        return digitBuilder;
//    }
//
//    private int getNumberFromDividend() {
//    	return convertToInt(digitsFromDividend[currentIndex]);
//    }
//
//    private int getDigitFromDividend() {
//    	StringBuilder sb = new StringBuilder();
//    	sb.append(digitsFromDividend[currentIndex])
//    		.append(getZeroAfterDigit());
//        return convertToInt(sb);
//    }

    private int convertToInt(char digitSymbol) {
        return Integer.parseInt(String.valueOf(digitSymbol));
    }

    private int convertToInt(StringBuilder stringBuilder) {
        return Integer.parseInt(String.valueOf(stringBuilder));
    }

    private void divideNumbers(DivisionStep.Builder stepBuilder) {
        int subNumber = 0;
        int multiplier = 0;

        while (subNumber <= stepBuilder.getDigit()) {
            multiplier++;
            subNumber = divider * multiplier;
        }
        multiplier--;

        subNumber = divider * multiplier;
        stepBuilder.setMultiplier(multiplier)
                .setSubNumber(subNumber);
    }
    
    // upper multiplier? #TODO
    private int calcMultiplier(int subtractedNumber) {
        return subtractedNumber / divider;
    }

    private int calcRemainder(int digit, int subtractedNumber) {
        return digit - subtractedNumber;
    }

}
