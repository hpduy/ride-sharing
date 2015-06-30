package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/25/15.
 */
public class Rating {
    private long id;
    private String assessorId = "";
    private long profileId;
    private int value;
    private long createdTime;
    private String comment = "";
    //final field
    @JsonIgnore
    public static final int POSITIVE = 1;
    @JsonIgnore
    public static final int NEUTRAL = 0;
    @JsonIgnore
    public static final int NEGATIVE = -1;

    public Rating() {
    }

    public Rating(long id, String assessorId, long profileId, int value, long createdTime, String comment) {
        this.id = id;
        this.assessorId = assessorId == null ? "" : assessorId;
        this.profileId = profileId;
        this.value = value;
        this.createdTime = createdTime;
        this.comment = comment == null ? "" : comment;
    }

    public Rating(Rating that) {
        this.id = that.id;
        this.assessorId = that.assessorId == null ? "" : that.assessorId;
        this.profileId = that.profileId;
        this.value = that.value;
        this.createdTime = that.createdTime;
        this.comment = that.comment == null ? "" : that.comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(String assessorId) {
        this.assessorId = assessorId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
