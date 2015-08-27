package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPopularLocationRequest {

    private String userId;
    private double lat;
    private double lng;
    private String name;
    private String address;
    private String imagePath;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public AddPopularLocationRequest() {
    }

    public AddPopularLocationRequest(String userId, double lat, double lng, String name, String address, String imagePath) {
        this.userId = userId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.address = address;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }

}
