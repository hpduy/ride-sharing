package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

    private String email;
    private String password;
    private String facebookId;
    private String googleId;
    private String twitterId;
    private String linkedInId;
    private String selfIntro;
    private String firstName;
    private String lastName;
    private String profilePictureLink;
    private String phone;
    private String birthDay;
    private int gender;
    private int type;
    //final field
    @JsonIgnore
    public static final int EMAIL = 1;
    @JsonIgnore
    public static final int FACEBOOK = 2;
    @JsonIgnore
    public static final int GOOGLE = 3;
    @JsonIgnore
    public static final int TWITTER = 4;
    @JsonIgnore
    public static final int LINKEDIN = 5;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String facebookId, String googleId, String twitterId, String linkedInId, String selfIntro, String firstName, String lastName, String profilePictureLink, String phone, String birthDay, int gender, int type) {
        this.email = email;
        this.password = password;
        this.facebookId = facebookId;
        this.googleId = googleId;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.selfIntro = selfIntro;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureLink = profilePictureLink;
        this.phone = phone;
        this.birthDay = birthDay;
        this.gender = gender;
        this.type = type;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
