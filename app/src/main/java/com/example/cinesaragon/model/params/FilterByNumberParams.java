package com.example.cinesaragon.model.params;

public class FilterByNumberParams {

    private final Mode filtering;
    private final int threshold;

    public FilterByNumberParams(Mode filtering, int threshold) {
        this.filtering = filtering;
        this.threshold = threshold;
    }

    public Mode getFiltering() {
        return filtering;
    }

    public int getThreshold() {
        return threshold;
    }

    public enum Mode {
        GREATER_THAN,
        LESSER_THAN
    }
}
