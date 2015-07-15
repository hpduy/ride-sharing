package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/29/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUsersAroundFromMeRequest {
    private String userId;
    private double centerLat;
    private double centerLng;
    private double radius;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }

    public double getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(double centerLng) {
        this.centerLng = centerLng;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
