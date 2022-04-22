package com.calculator.integerdivision.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DivisionMathResult implements Result<Integer> {
    private final int dividend;
    private final int divider;
    private final List<DivisionStep> steps = new ArrayList<>();

    public DivisionMathResult(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    /**
     * @param index step index;
     * @return DivisionStep object if object by passed index exists
     * in the step list, otherwise returns null pointer;
     * */
    public DivisionStep getStep(int index) {
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

    @Override
    public Integer getResult() {
        StringBuilder result = new StringBuilder();
        for (DivisionStep step : steps) {
            result.append(step.getMultiplier()); // ww
        }
        return Integer.parseInt(result.toString());
    }

    public List<DivisionStep> getDivisionSteps() {
        return steps;
    }

    public DivisionMathResult addStep(DivisionStep divisionStep) {
        steps.add(divisionStep);
        return this;
    }

    public DivisionMathResult addAllSteps(DivisionStep... steps) {
        Collections.addAll(this.steps, steps);
        return this;
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
        return steps.equals(that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(steps);
    }
}
