package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/29/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUsersAroundFromMeRequest {
    private double centerLat;
    private double centerLng;
    private double radius;
    private String userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetUsersAroundFromMeRequest() {
    }

    public GetUsersAroundFromMeRequest(double centerLat, double centerLng, double radius, String userId) {
        this.centerLat = centerLat;
        this.centerLng = centerLng;
        this.radius = radius;
        this.userId = userId;
    }
     @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
       
}
