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
     * @return DivisionMathStep object if it exists in the step list
     * by passed index, otherwise returns null
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

    /**
     * Returns result of integer division that consists of
     * the multipliers of each DivisionMathStep object. All the multipliers
     * are concat to the string which is converts to integer.
     *
     * @return integer > 0 when multiplier string are converted to integer correctly.
     * Returns integer == 0 when multiplier string represents too many numbers (> 10)
     * to be converted to integer.
     */
    public int getResult() {
        String concatMultipliers = steps.stream()
                .map(step -> String.valueOf(step.getMultiplier()))
                .reduce("", (partial, next) -> partial + next);
        try {
            return Integer.parseInt(concatMultipliers);
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
        return "DivisionMathResult{" +
                "dividend=" + dividend +
                ", divider=" + divider +
                ", result=" + getResult() +
                ", steps=" + steps +
                "}";
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
        if (getResult() != that.getResult()) {
            return false;
        }
        if (size() != that.size()) {
            return false;
        }
        return steps.equals(that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(steps);
    }

}
