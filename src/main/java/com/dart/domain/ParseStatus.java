package com.dart.domain;

/**
 * Created by dart on 20.10.17.
 */
public enum ParseStatus {

    PARSED("Parsed"),
    PARSING("Parsing"),
    NOT_FOUND("Not found"),
    FAILED("Failed");

    private String label;

    ParseStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
