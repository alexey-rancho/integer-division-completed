package com.calculator.integerdivision.domain.math;

import java.util.Objects;

public final class DivisionMathStep {

    private final int multiplier;
    private final int digit;
    private final int subNumber;
    private final int remainder;

    private DivisionMathStep(Builder builder) {
        multiplier = builder.multiplier;
        digit = builder.digit;
        subNumber = builder.subNumber;
        remainder = builder.remainder;
    }

    public int getDigit() {
        return digit;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getSubNumber() {
        return subNumber;
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

        public DivisionMathStep build() {
            return new DivisionMathStep(this);
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

        DivisionMathStep that = (DivisionMathStep) o;

        if (multiplier != that.multiplier) {
            return false;
        }
        if (digit != that.digit) {
            return false;
        }
        if (subNumber != that.subNumber) {
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
        return "DivisionStep{" +
                "multiplier=" + multiplier +
                ", digit=" + digit +
                ", subNumber=" + subNumber +
                ", remainder=" + remainder +
                '}';
    }

}
