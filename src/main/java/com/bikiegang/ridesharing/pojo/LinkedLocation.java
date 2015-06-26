package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class LinkedLocation extends Location {
    private int index;
    private long refId; // routeId or tripId
    @JsonIgnore
    private int refType;
    //final field
    @JsonIgnore
    private static final int FREE = 0;
    @JsonIgnore
    private static final int IN_ROUTE = 1;
    @JsonIgnore
    private static final int IN_TRIP = 2;

    public LinkedLocation() {
    }

    public LinkedLocation(double lat, double lng, long id, long createdTime, String address, int index, long refId, int refType) {
        super(lat, lng, id, createdTime, address);
        this.index = index;
        this.refId = refId;
        this.refType = refType;
    }
    public LinkedLocation(LinkedLocation that) {
        super(that.lat, that.lng, that.id, that.createdTime, that.address);
        this.index = that.index;
        this.refId = that.refId;
        this.refType = that.refType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public int getRefType() {
        return refType;
    }

    public void setRefType(int refType) {
        this.refType = refType;
    }
}
