package com.calculator.integerdivision.domain.view;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DivisionViewStep {
    private final List<String> viewLines;

    private DivisionViewStep(Builder builder) {
        this.viewLines = builder.viewLines;
    }

    public List<String> getViewLines() {
        return viewLines;
    }

    public String getView() {
        return String.join("", viewLines);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final List<String> viewLines = new ArrayList<>();

        private Builder() {
        }

        public Builder addViewLine(String line) {
            this.viewLines.add(line);
            return this;
        }

        public Builder addAllViewLines(String... lines) {
            Collections.addAll(viewLines, lines);
            return this;
        }

        public DivisionViewStep build() {
            return new DivisionViewStep(this);
        }
    }

    @Override
    public String toString() {
        return String.join("", viewLines);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }

        DivisionViewStep that = (DivisionViewStep) o;

        return getViewLines().equals(that.getViewLines());
    }
}
