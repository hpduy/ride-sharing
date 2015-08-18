package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 8/17/15.
 */
public class SocialTripAttendance {

    private long id;
    private String userId;
    private long socialTripId;
    private int role; // user role

    public SocialTripAttendance() {
    }

    public SocialTripAttendance(long id, String userId, long socialTripId, int role) {
        this.id = id;
        this.userId = userId;
        this.socialTripId = socialTripId;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSocialTripId() {
        return socialTripId;
    }

    public void setSocialTripId(long socialTripId) {
        this.socialTripId = socialTripId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
