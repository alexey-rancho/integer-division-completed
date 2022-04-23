package com.calculator.integerdivision.domain;

import java.util.ArrayList;
import java.util.List;

public interface DivisionResult<StepType> {

    StepType getStep(int index);

    List<StepType> getSteps();

    DivisionResult<StepType> addStep(StepType step);

    DivisionResult<StepType> addAllSteps(StepType... steps);

}
