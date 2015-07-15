package com.bikiegang.ridesharing.geocoding.GoogleRoutingObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Duration {
    private String text;
    private long value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
