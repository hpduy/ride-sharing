package com.bikiegang.ridesharing.geocoding.GoogleRoutingObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Time {
    private String text;
    private String time_zone;
    private long value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
