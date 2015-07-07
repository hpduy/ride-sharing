package com.bikiegang.ridesharing.pojo;


/**
 * Created by hpduy17 on 6/30/15.
 */
public class RequestMakeTrip implements PojoBase {
    private long id;
    private String senderId = "";
    private String receiverId = "";
    private long createdTime;
    private long senderRouteId;
    private int status;

  

    public RequestMakeTrip() {
    }

    public RequestMakeTrip(long id, String senderId, String receiverId, long createdTime, long senderRouteId, int status) {
        this.id = id;
        this.senderId = senderId == null ? "" : senderId;
        this.receiverId = receiverId == null ? "" : receiverId;
        this.createdTime = createdTime;
        this.senderRouteId = senderRouteId;
        this.status = status;
    }

    public RequestMakeTrip(RequestMakeTrip that) {
        this.id = that.id;
        this.senderId = that.senderId == null ? "" : that.senderId;
        this.receiverId = that.receiverId == null ? "" : that.receiverId;
        this.createdTime = that.createdTime;
        this.senderRouteId = that.senderRouteId;
        this.status = that.status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getSenderRouteId() {
        return senderRouteId;
    }

    public void setSenderRouteId(long senderRouteId) {
        this.senderRouteId = senderRouteId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
