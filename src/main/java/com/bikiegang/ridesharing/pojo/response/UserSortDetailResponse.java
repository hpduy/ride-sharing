package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Path;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserSortDetailResponse {
    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private String profilePictureLink = "";
    private LatLng currentLocation = new LatLng();
    private String birthDay;
    private int status;

    public UserSortDetailResponse() {
    }

    public UserSortDetailResponse(User user) {
        if(null != user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.profilePictureLink = Path.getProfilePictureUrlFromPath(user.getProfilePictureLink());
            this.currentLocation = user.getCurrentLocation();
            this.birthDay = user.getBirthDay();
            this.status = user.getStatus();
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


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
