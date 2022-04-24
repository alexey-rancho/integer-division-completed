package com.calculator.integerdivision.domain.math;

import com.calculator.integerdivision.domain.DivisionResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DivisionMathResult implements DivisionResult<DivisionMathStep> {
    private final int dividend;
    private final int divider;
    private final List<DivisionMathStep> steps = new ArrayList<>();

    public DivisionMathResult(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    /**
     * @param index step index;
     * @return DivisionStep object if object by passed index exists
     * in the step list, otherwise returns null pointer;
     */
    @Override
    public DivisionMathStep getStep(int index) {
        try {
            return steps.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivider() {
        return divider;
    }

    public int getResult() {
        StringBuilder result = new StringBuilder();
        for (DivisionMathStep step : steps) {
            result.append(step.getMultiplier());
        }
        try {
            return Integer.parseInt(result.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public List<DivisionMathStep> getSteps() {
        return List.copyOf(steps);
    }

    @Override
    public DivisionMathResult addStep(DivisionMathStep step) {
        steps.add(step);
        return this;
    }

    @Override
    public DivisionMathResult addAllSteps(DivisionMathStep... steps) {
        Collections.addAll(this.steps, steps);
        return this;
    }

    @Override
    public int size() {
        return steps.size();
    }

    @Override
    public String toString() {
        return new StringBuilder("DivisionMathResult{")
                .append("dividend=")
                .append(dividend)
                .append(", ")
                .append("divider=")
                .append(divider)
                .append(", ")
                .append("result=")
                .append(getResult())
                .append(", ")
                .append("steps=")
                .append(steps)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DivisionMathResult that = (DivisionMathResult) o;

        if (dividend != that.dividend) {
            return false;
        }
        if (divider != that.divider) {
            return false;
        }
        if (!(getResult() == that.getResult())) {
            return false;
        }
        return steps.equals(that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(steps);
    }
}
