package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePlannedTripRequest {
    private String creatorId;
    private int role;
    private long goTime;
    private double price = -1;
    private String googleRoutingResult;
    private boolean isParing = true;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }

    public boolean isParing() {
        return isParing;
    }

    public void setIsParing(boolean isParing) {
        this.isParing = isParing;
    }
}
