package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/2/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateProfileRequest {
    private String id;
    private String selfIntro;
    private String firstName;
    private String lastName;
    private String profilePictureLink;
    private String phone ;
    private String birthDay;
    private String job;
    private String organizationId;
    private int gender = -1;
    private int privacy = -1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String id, String selfIntro, String firstName, String lastName, String profilePictureLink, String phone, String birthDay, String job) {
        this.id = id;
        this.selfIntro = selfIntro;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureLink = profilePictureLink;
        this.phone = phone;
        this.birthDay = birthDay;
        this.job = job;
    }
     @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
