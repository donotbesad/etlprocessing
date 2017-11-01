package com.dart.domain;

/**
 * Author: Dmitry Artemenko
 * Date: 20.10.17
 * Time: 14:09
 *
 * @author Dmitry Artemenko
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
