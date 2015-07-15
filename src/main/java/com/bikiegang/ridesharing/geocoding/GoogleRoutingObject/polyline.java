package com.bikiegang.ridesharing.geocoding.GoogleRoutingObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Polyline {
    private String points;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
