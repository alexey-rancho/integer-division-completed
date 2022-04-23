package com.calculator.integerdivision.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DivisionViewResult implements DivisionResult<DivisionViewStep> {

    private final List<DivisionViewStep> steps = new ArrayList<>();

    @Override
    public DivisionViewStep getStep(int index) {
        try {
            return steps.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public DivisionViewResult addStep(DivisionViewStep step) {
        steps.add(step);
        return this;
    }

    @Override
    public List<DivisionViewStep> getSteps() {
        return steps;
    }

    @Override
    public DivisionViewResult addAllSteps(DivisionViewStep... steps) {
        Collections.addAll(this.steps, steps);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        steps.forEach(step -> builder.append(step.getView()));
        return builder.toString();
    }
}
