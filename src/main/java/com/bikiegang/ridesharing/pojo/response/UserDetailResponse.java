package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailResponse extends UserShortDetailResponse {
    private int gender;
    private String phone;
    private String selfIntro;
    private LatLng currentLocation = new LatLng();
    public UserDetailResponse() {

    }

    public UserDetailResponse(User user) {
        super(user);
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.selfIntro = user.getSelfIntro();
        this.currentLocation = user.getCurrentLocation();
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
    }
}
