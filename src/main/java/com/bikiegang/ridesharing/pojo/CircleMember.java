package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class CircleMember {
    private long id;
    private long circleId;
    private String memberId;
    private long createdTime;

    public CircleMember() {
    }

    public CircleMember(long id, long circleId, String memberId, long createdTime) {
        this.id = id;
        this.circleId = circleId;
        this.memberId = memberId;
        this.createdTime = createdTime;
    }
    public CircleMember(CircleMember that) {
        this.id = that.id;
        this.circleId = that.circleId;
        this.memberId = that.memberId;
        this.createdTime = that.createdTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCircleId() {
        return circleId;
    }

    public void setCircleId(long circleId) {
        this.circleId = circleId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
