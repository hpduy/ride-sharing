package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/29/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRouteRequest {
    private long goTime;
    private long arriveTime;
    private double price;
    private long expiredTime; // use for multiple future
    private double lat;
    private double lng;
    private String creatorId;
    private LatLng[] locations;
    private int type;

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LatLng[] getLocations() {
        return locations;
    }

    public void setLocations(LatLng[] locations) {
        this.locations = locations;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
