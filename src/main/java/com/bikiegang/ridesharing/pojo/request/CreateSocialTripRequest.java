package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/20/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSocialTripRequest {
    private String userId;
    private double lat;
    private double lng;
    private String feeling;
    private int feelingIcon;
    private String wantToGo;
    private int wantToGoIcon;
    private String content = "";
    private int role;

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

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public int getFeelingIcon() {
        return feelingIcon;
    }

    public void setFeelingIcon(int feelingIcon) {
        this.feelingIcon = feelingIcon;
    }

    public String getWantToGo() {
        return wantToGo;
    }

    public void setWantToGo(String wantToGo) {
        this.wantToGo = wantToGo;
    }

    public int getWantToGoIcon() {
        return wantToGoIcon;
    }

    public void setWantToGoIcon(int wantToGoIcon) {
        this.wantToGoIcon = wantToGoIcon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
