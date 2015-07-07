package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class LinkedLocation extends Location implements PojoBase {

    private int index;
    private long refId; // routeId or tripId
    private int refType;
    //final field

    @JsonIgnore
    public static final int FREE = 0;
    @JsonIgnore
    public static final int IN_ROUTE = 1;
    @JsonIgnore
    public static final int IN_TRIP = 2;

    public LinkedLocation() {
    }

    public LinkedLocation(Location location, int index, long refId, int refType) {
        super(location);
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
