package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Path;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserShortDetailResponse {
    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private String profilePictureLink = "";
    private String birthDay;
    private int verifyStatus;
    private String job;
    private String email;
    private int privacy;
    public UserShortDetailResponse() {
    }

    public UserShortDetailResponse(User user) {
        if (null != user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.profilePictureLink = Path.getUrlFromPath(user.getProfilePictureLink());
            this.job = user.getJob();
            this.birthDay = user.getBirthDay();
            this.verifyStatus = user.getStatus();
            this.privacy = user.getPrivacy();
            this.email = user.getEmail();
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
