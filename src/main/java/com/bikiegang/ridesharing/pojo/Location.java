package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class Location extends LatLng {
    protected long id;
    protected long createdTime;
    protected long estimatedTime;
    protected String address;
    public Location() {

    }

    public Location(double lat, double lng, long id, long createdTime, String address) {
        super(lat, lng);
        this.id = id;
        this.createdTime = createdTime;
        this.address = address;
    }

    public Location(Location that) {
        super(that.lat, that.lng);
        this.id = that.id;
        this.createdTime = that.createdTime;
        this.estimatedTime = that.estimatedTime;
        this.address = that.address;
    }

    public String test(){
        return "test";
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
