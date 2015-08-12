package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Path;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserShortDetailResponse {
    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private String profilePictureLink = "";
    private LatLng currentLocation = new LatLng();
    private String birthDay;
    private int verifyStatus;

    public UserShortDetailResponse() {
    }

    public UserShortDetailResponse(User user) {
        if(null != user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.profilePictureLink = Path.getUrlFromPath(user.getProfilePictureLink());
            this.currentLocation = user.getCurrentLocation();
            this.birthDay = user.getBirthDay();
            this.verifyStatus = user.getStatus();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
