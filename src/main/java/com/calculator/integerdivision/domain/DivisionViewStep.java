package com.calculator.integerdivision.domain;

public class DivisionViewStep {
    private final String view;

    private DivisionViewStep(Builder builder) {
        this.view = builder.view;
    }

    public String getView() {
        return view;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String view;

        private Builder() {
        }

        public Builder setView(String view) {
            this.view = view;
            return this;
        }

        public DivisionViewStep build() {
            return new DivisionViewStep(this);
        }
    }
}
