package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 8/18/15.
 */
public class Feed implements PojoBase {

    private long id;
    private int type;
    private long refId;

    @JsonIgnore
    public static final int PLANNED_TRIP = 1;
    @JsonIgnore
    public static final int SOCIAL_TRIP = 2;

    public Feed() {
    }

    public Feed(long id, int type, long refId) {
        this.id = id;
        this.type = type;
        this.refId = refId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }
}
