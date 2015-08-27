package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class LinkedLocation extends LatLng implements PojoBase {
    private long id;
    private long estimatedTime; // duration from first location
    private int index;
    private long refId; // routeId

    public LinkedLocation() {

    }


    public LinkedLocation(double lat, double lng, long time, long id, long estimatedTime, int index, long refId) {
        super(lat, lng, time);
        this.id = id;
        this.estimatedTime = estimatedTime;
        this.index = index;
        this.refId = refId;
    }
    public LinkedLocation(LinkedLocation that) {
        super(that.lat, that.lng, that.time);
        this.id = that.id;
        this.estimatedTime = that.estimatedTime;
        this.index = that.index;
        this.refId = that.refId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }
}
