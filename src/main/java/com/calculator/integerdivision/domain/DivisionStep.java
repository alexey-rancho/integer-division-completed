package com.calculator.integerdivision.domain;


import java.util.Objects;
//TODO: how to get rid of redundant getters?
public final class DivisionStep {

    private final int multiplier;
    private final int digit;
    private final int subtractedNumber;
    private final int remainder;

    private DivisionStep(Builder builder) {
        multiplier = builder.multiplier;
        digit = builder.digit;
        subtractedNumber = builder.subNumber;
        remainder = builder.remainder;
    }

    public int getDigit() {
        return digit;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getSubtractedNumber() {
        return subtractedNumber;
    }

    public int getRemainder() {
        return remainder;
    }
    
    public static Builder newBuilder() {
    	return new Builder();
    }
    
    public static class Builder {

        private int multiplier;
        private int digit;
        private int subNumber;

        private int remainder;
    	private Builder() {
    		// private constructor
    	}

        public int getMultiplier() {
            return multiplier;
        }

        public int getDigit() {
            return digit;
        }

        public int getSubNumber() {
            return subNumber;
        }

        public int getRemainder() {
            return remainder;
        }


    	public Builder setDigit(int digit) {
    		this.digit = digit;
    		return this;
    	}

    	public Builder setMultiplier(int multiplier) {
    		this.multiplier = multiplier;
    		return this;
    	}

    	public Builder setSubNumber(int subNumber) {
    		this.subNumber = subNumber;
    		return this;
    	}

    	public Builder setRemainder(int remainder) {
    		this.remainder = remainder;
    		return this;
    	}

    	public DivisionStep build() {
    		return new DivisionStep(this);
    	}

        @Override
        public String toString() {
            return "Builder{" +
                    "multiplier=" + multiplier +
                    ", digit=" + digit +
                    ", subNumber=" + subNumber +
                    ", remainder=" + remainder +
                    '}';
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }

        DivisionStep that = (DivisionStep) o;

        if (multiplier != that.multiplier) {
            return false;
        }
        if (digit != that.digit) {
            return false;
        }
        if (subtractedNumber != that.subtractedNumber) {
            return false;
        }
        return remainder == that.remainder;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public String toString() {
        return new StringBuilder("DivisionStep{")
                .append("multiplier=")
                .append(multiplier)
                .append(", digit=")
                .append(digit)
                .append(", subtractedNumber=")
                .append(subtractedNumber)
                .append(", remainder=")
                .append(remainder)
                .append('}')
                .toString();
    }

}
