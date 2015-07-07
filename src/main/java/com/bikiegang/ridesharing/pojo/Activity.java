package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/25/15.
 */
public class Activity implements PojoBase {

    private long id;
    private String ownerId = "";
    private String posUserId = "";
    private String negUserId = "";
    private long createdTime;
    private int action;
    private long ref;
    private boolean isRead;
    //final field

    public Activity() {
    }

    public Activity(long id, String ownerId, String posUserId, String negUserId, long createdTime, int action, long ref, boolean isRead) {
        this.id = id;
        this.ownerId = ownerId == null ? "" : ownerId;
        this.posUserId = posUserId == null ? "" : posUserId;
        this.negUserId = negUserId == null ? "" : negUserId;
        this.createdTime = createdTime;
        this.action = action;
        this.ref = ref;
        this.isRead = isRead;
    }

    public Activity(Activity that) {
        this.id = that.id;
        this.ownerId = that.ownerId == null ? "" : that.ownerId;
        this.posUserId = that.posUserId == null ? "" : that.posUserId;
        this.negUserId = that.negUserId == null ? "" : that.negUserId;
        this.createdTime = that.createdTime;
        this.action = that.action;
        this.ref = that.ref;
        this.isRead = that.isRead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPosUserId() {
        return posUserId;
    }

    public void setPosUserId(String posUserId) {
        this.posUserId = posUserId;
    }

    public String getNegUserId() {
        return negUserId;
    }

    public void setNegUserId(String negUserId) {
        this.negUserId = negUserId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
