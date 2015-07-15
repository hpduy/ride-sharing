package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Path;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class SortUserDetailResponse {
    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private String profilePicture = "";
    private String phone = "";
    private LatLng currentLocation = new LatLng();
    private long birthDay;
    private int gender;
    private int status;
    private int currentRole;

    public SortUserDetailResponse() {
    }

    public SortUserDetailResponse(User user) {
        if(null != user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.profilePicture = Path.getProfilePictureUrlFromPath(user.getProfilePictureLink());
            this.phone = user.getPhone();
            this.currentLocation = user.getCurrentLocation();
            this.birthDay = user.getBirthDay();
            this.gender = user.getGender();
            this.status = user.getStatus();
            this.currentRole = user.getCurrentRole();
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
    }

    public long getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(long birthDay) {
        this.birthDay = birthDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(int currentRole) {
        this.currentRole = currentRole;
    }
}
