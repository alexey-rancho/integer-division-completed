package com.calculator.integerdivision.domain;


public final class DivisionStep {

    private final int index;
    private final int multiplier;
    private final int digit;
    private final int subtractedNumber;
    private final int remainder;

    private DivisionStep(Builder builder) {
        index = builder.index;
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

    public int getIndex() {
        return index;
    }
    
    public static Builder newBuilder() {
    	return new Builder();
    }
    
    public static class Builder {
    	private int index;

        private int multiplier;

        private int digit;
        private int subNumber;

        private int remainder;

    	private Builder() {
    		// private constructor
    	}

        public int getIndex() {
            return index;
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

    	public Builder setIndex(int index) {
    		this.index = index;
    		return this;
    	}

    	public DivisionStep build() {
    		return new DivisionStep(this);
    	}

        @Override
        public String toString() {
            return "Builder{" +
                    "index=" + index +
                    ", multiplier=" + multiplier +
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

        if (index != that.index) {
            return false;
        }
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
        int result = index;
        result = 31 * result + multiplier;
        result = 31 * result + digit;
        result = 31 * result + subtractedNumber;
        result = 31 * result + remainder;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DivisionStep{");
        sb.append("index=")
        	.append(index)
        	.append(", multiplier=")
        	.append(multiplier)
        	.append(", digit=")
        	.append(digit)
        	.append(", subtractedNumber=")
        	.append(subtractedNumber)
        	.append(", remainder=")
        	.append(remainder)
        	.append('}');
        return sb.toString();
    }

}
