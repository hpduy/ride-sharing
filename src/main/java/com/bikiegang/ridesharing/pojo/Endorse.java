package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Endorse  implements PojoBase{
    private long id;
    private String endorserId = "";
    private String endorsedUserId = "";
    private long circleId;
    private String comment = "";
    private long createdTime;

    public Endorse() {
    }

    public Endorse(long id, String endorserId, String endorsedUserId, long circleId, String comment, long createdTime) {
        this.id = id;
        this.endorserId = endorserId == null ? "" : endorserId;
        this.endorsedUserId = endorsedUserId == null ? "" : endorsedUserId;
        this.circleId = circleId;
        this.comment = comment == null ? "" : comment;
        this.createdTime = createdTime;
    }

    public Endorse(Endorse that) {
        this.id = that.id;
        this.endorserId = that.endorserId == null ? "" : that.endorserId;
        this.endorsedUserId = that.endorsedUserId == null ? "" : that.endorsedUserId;
        this.circleId = that.circleId;
        this.comment = that.comment == null ? "" : that.comment;
        this.createdTime = that.createdTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEndorserId() {
        return endorserId;
    }

    public void setEndorserId(String endorserId) {
        this.endorserId = endorserId;
    }

    public String getEndorsedUserId() {
        return endorsedUserId;
    }

    public void setEndorsedUserId(String endorsedUserId) {
        this.endorsedUserId = endorsedUserId;
    }

    public long getCircleId() {
        return circleId;
    }

    public void setCircleId(long circleId) {
        this.circleId = circleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
