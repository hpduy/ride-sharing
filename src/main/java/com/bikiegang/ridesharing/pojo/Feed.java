package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 8/18/15.
 */
public class Feed implements PojoBase , Comparable{

    private long id;
    private int type;
    private long refId;
    private long createdTime;

    @JsonIgnore
    public static final int PLANNED_TRIP = 1;
    @JsonIgnore
    public static final int SOCIAL_TRIP = 2;

    public Feed() {
    }

    public Feed(long id, int type, long refId, long createdTime) {
        this.id = id;
        this.type = type;
        this.refId = refId;
        this.createdTime = createdTime;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public int compareTo(Object o) {
        Feed that = (Feed) o;
        if (this.getCreatedTime() < that.getCreatedTime())
            return -1;
        return 1;
    }
}
