package com.calculator.integerdivision.provider;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.domain.DivisionResult;
import com.calculator.integerdivision.domain.DivisionStep;

public class DivisionMathProvider implements Provider<DivisionMathResult> {

    private int dividend;
    private int divider;
    private char[] digitsFromDividend;
    private int dividendLength;
    private int remainder;
    private int currentIndex = 0;
    private int lastIndex;

    public DivisionMathProvider(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    public DivisionMathResult provide() {
        DivisionMathResult divisionResult = new DivisionMathResult(dividend, divider);

        digitsFromDividend = String.valueOf(dividend).toCharArray();
        dividendLength = digitsFromDividend.length;
        lastIndex = dividendLength - 1;

        while (currentIndex < dividendLength) {
            DivisionStep divisionStep = divideNumbers();
            divisionResult.addDivisionStep(divisionStep);
        }

        return divisionResult;
    }

    private DivisionStep divideNumbers() {

        int subtractedNumber = 0;
        int multiplier = 0;
        int digit = getDigitFromDividend();
        
        if (digit >= divider) {
            StringBuilder digitBuilder = new StringBuilder();

            if (remainder > 0) {
                digitBuilder.append(remainder);
            }
            digitBuilder.append(digit);
            digitBuilder = getNumberAfterDecimal(digitBuilder);

            digit = convertToInt(digitBuilder);

            subtractedNumber = calcSubtractedNumber(digit);
            remainder = calcRemainder(digit, subtractedNumber);
            multiplier = calcMultiplier(subtractedNumber);
        }
        if (digit < divider) {
            StringBuilder digitBuilder = new StringBuilder();

            if (remainder > 0) {
                digitBuilder.append(remainder);
            }
            digitBuilder.append(digit);
            digitBuilder = getNumberAfterDecimal(digitBuilder);

            digit = convertToInt(digitBuilder);

            while (digit < divider && currentIndex < lastIndex) {
                currentIndex++;
                digitBuilder.append(getNumberFromDividend());
                digit = convertToInt(digitBuilder);
            }

            if (digit > divider) {
                subtractedNumber = calcSubtractedNumber(digit);
                remainder = calcRemainder(digit, subtractedNumber);
                multiplier = calcMultiplier(subtractedNumber);
            }
            
            if (digit == divider) { // why else if #TODO
                digitBuilder.append(getZeroAfterDigit());

                subtractedNumber = calcSubtractedNumber(digit);
                remainder = calcRemainder(digit, subtractedNumber);
                multiplier = calcMultiplier(subtractedNumber);
            }
        }

        currentIndex++;

        return DivisionStep.newBuilder()
        		.setDigit(digit)
        		.setIndex(currentIndex)
        		.setMultiplier(multiplier)
        		.setRemainder(remainder)
        		.setSubtractedNumber(subtractedNumber)
        		.build();
    }
    
    private StringBuilder getNumberAfterDecimal(StringBuilder digitBuilder) {
    	if (currentIndex == lastIndex) {
    		return digitBuilder;
    	}
    	
    	currentIndex++;
    	int number = getNumberFromDividend();
    	if (number < divider) {
    		StringBuilder newDigitBuilder = new StringBuilder(digitBuilder);
    		newDigitBuilder.append(number);
    		return newDigitBuilder;
    	} else {
    		currentIndex--;
    		return digitBuilder;	
    	}
    }
    
    private StringBuilder getZeroAfterDigit() {
        StringBuilder digitBuilder = new StringBuilder();

        while (currentIndex < lastIndex) {
            currentIndex++;
            int guessNumber = getNumberFromDividend();
            if (guessNumber == 0) {
                digitBuilder.append(guessNumber);
            } else {
                currentIndex--;
                break;
            }
        }

        return digitBuilder;
    }
    
    private int getNumberFromDividend() {
    	return convertToInt(digitsFromDividend[currentIndex]);
    }

    private int getDigitFromDividend() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(digitsFromDividend[currentIndex])
    		.append(getZeroAfterDigit());
        return convertToInt(sb);
    }

    private int convertToInt(char digitSymbol) {
        return Integer.parseInt(String.valueOf(digitSymbol));
    }

    private int convertToInt(StringBuilder stringBuilder) {
        return Integer.parseInt(String.valueOf(stringBuilder));
    }

    private int calcSubtractedNumber(int digit) {
        int subtractedNumber = 0;
        int multiplier = 0;

        while (subtractedNumber <= digit) {
            multiplier++;
            subtractedNumber = divider * multiplier;
        }
        multiplier--;
        subtractedNumber = divider * multiplier;

        return subtractedNumber;
    }
    
    // upper multiplier? #TODO
    private int calcMultiplier(int subtractedNumber) {
        return subtractedNumber / divider;
    }

    private int calcRemainder(int digit, int subtractedNumber) {
        return digit - subtractedNumber;
    }

}
