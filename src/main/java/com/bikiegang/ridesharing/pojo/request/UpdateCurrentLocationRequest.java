package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCurrentLocationRequest {

    protected double lat;
    protected double lng;
    protected String userId;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UpdateCurrentLocationRequest() {
    }

    public UpdateCurrentLocationRequest(double lat, double lng, String userId) {
        this.lat = lat;
        this.lng = lng;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
