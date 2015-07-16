package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class User implements PojoBase {

    private String id = "";
    private String email = "";
    private String password = "";
    private String facebookId = "";
    private String googleId = "";
    private String linkedInId = "";
    private String selfIntro = "";
    private String twitterId = "";
    private String firstName = "";
    private String lastName = "";
    private String profilePictureLink = "";
    private String phone = "";
    private LatLng currentLocation = new LatLng();
    private String birthDay;
    private int gender;
    private int status;
    private boolean isBusy;
    private int currentRole;

    // final field & dont print to JSON;
    /**
     * GENDER
     */
    @JsonIgnore
    public static final int UNKNOWN = 0;
    @JsonIgnore
    public static final int MALE = 1;
    @JsonIgnore
    public static final int FEMALE = 2;
    /**
     * STATUS
     */
    @JsonIgnore
    public static final int UNVERIFIED = 0;
    @JsonIgnore
    public static final int VERIFIED = 1;
    /**
     * USER ROLE
     */
    @JsonIgnore
    public static final int DRIVER = 1;
    @JsonIgnore
    public static final int PASSENGER = 2;

    public User() {

    }

    public User(String id, String email, String password, String facebookId, String googleId, String twitterId, String linkedInId, String selfIntro, String firstName, String lastName, String profilePictureLink, String phone, String birthDay, int gender, int status, boolean isBusy) {
        this.id = id == null ? "" : id;
        this.email = email == null ? "" : email;
        this.password = password == null ? "" : password;
        this.facebookId = facebookId == null ? "" : facebookId;
        this.googleId = googleId == null ? "" : googleId;
        this.twitterId = twitterId == null ? "" : twitterId;
        this.linkedInId = linkedInId == null ? "" : linkedInId;
        this.selfIntro = selfIntro == null ? "" : selfIntro;
        this.firstName = firstName == null ? "" : firstName;
        this.lastName = lastName == null ? "" : lastName;
        this.profilePictureLink = profilePictureLink == null ? "" : profilePictureLink;
        this.phone = phone == null ? "" : phone;
        this.birthDay = birthDay;
        this.gender = gender;
        this.status = status;
        this.isBusy = isBusy;
    }

    public User(User that) {
        this.id = that.id == null ? "" : that.id;
        this.email = that.email == null ? "" : that.email;
        this.password = that.password == null ? "" : that.password;
        this.facebookId = that.facebookId == null ? "" : that.facebookId;
        this.googleId = that.googleId == null ? "" : that.googleId;
        this.twitterId = that.twitterId == null ? "" : that.twitterId;
        this.linkedInId = that.linkedInId == null ? "" : that.linkedInId;
        this.selfIntro = that.selfIntro == null ? "" : that.selfIntro;
        this.firstName = that.firstName == null ? "" : that.firstName;
        this.lastName = that.lastName == null ? "" : that.lastName;
        this.profilePictureLink = that.profilePictureLink == null ? "" : that.profilePictureLink;
        this.phone = that.phone == null ? "" : that.phone;
        this.birthDay = that.birthDay;
        this.gender = that.gender;
        this.currentRole = that.currentRole;
        this.currentLocation = that.currentLocation;
        this.status = that.status;
        this.isBusy = that.isBusy;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(int currentRole) {
        this.currentRole = currentRole;
    }

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
}
