package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.pojo.type.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class User {
    private String id;
    private String email;
    private String password;
    private String facebookId;
    private String googleId;
    private String twitterId;
    private String firstName;
    private String lastName;
    private long currentLocationId;
    private long birthDay;
    private int gender;
    private int status;
    //always change field
    @JsonIgnore
    private UserRole currentRole;
    // final field & dont print to JSON;
    /**GENDER*/
    @JsonIgnore
    public static final int UNKNOWN = 0;
    @JsonIgnore
    public static final int MALE = 1;
    @JsonIgnore
    public static final int FEMALE = 2;
    /**STATUS*/
    @JsonIgnore
    public static final int UNVERIFIED = 0;
    @JsonIgnore
    public static final int VERIFIED  = 1;


    public User() {
    }

    public User(String id, String email, String password, String facebookId, String googleId, String twitterId, String firstName, String lastName, long birthDay, int gender, int status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.facebookId = facebookId;
        this.googleId = googleId;
        this.twitterId = twitterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.status = status;
    }

    public User(User that) {
        this.id = that.id;
        this.email = that.email;
        this.password = that.password;
        this.facebookId = that.facebookId;
        this.googleId = that.googleId;
        this.twitterId = that.twitterId;
        this.firstName = that.firstName;
        this.lastName = that.lastName;
        this.birthDay = that.birthDay;
        this.gender = that.gender;
        this.currentRole = that.currentRole;
        this.currentLocationId = that.currentLocationId;
        this.status = that.status;
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

    public UserRole getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(UserRole currentRole) {
        this.currentRole = currentRole;
    }

    public long getCurrentLocationId() {
        return currentLocationId;
    }

    public void setCurrentLocationId(long currentLocationId) {
        this.currentLocationId = currentLocationId;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
