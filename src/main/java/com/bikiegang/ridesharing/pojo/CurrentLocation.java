package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class CurrentLocation extends Location {
    private String userId = "";
    private long previousLocationId;

    public CurrentLocation() {
    }

    public CurrentLocation(double lat, double lng, long id, long createdTime, String address, String userId, long previousLocationId) {
        super(lat, lng, id, createdTime, address);
        this.userId = userId == null ? "" : userId;
        this.previousLocationId = previousLocationId;
    }

    public CurrentLocation(CurrentLocation that) {
        super(that.lat, that.lng, that.id, that.createdTime, that.address);
        this.userId = that.userId == null ? "" : that.userId;
        this.previousLocationId = that.previousLocationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPreviousLocationId() {
        return previousLocationId;
    }

    public void setPreviousLocationId(long previousLocationId) {
        this.previousLocationId = previousLocationId;
    }
}
