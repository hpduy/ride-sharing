package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONObject;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRouteRequest {
    private String creatorId;
    private int role;
    private long goTime;
    private double price = -1;
    private JSONObject googleRoutingResult;

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

    public JSONObject getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(JSONObject googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
