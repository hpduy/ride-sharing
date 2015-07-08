package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Created by hpduy17 on 6/30/15.
 */
public class RequestMakeTrip implements PojoBase {
    private long id;
    private String senderId = "";
    private String receiverId = "";
    private long createdTime;
    private long driverRouteId;
    private long passengerRouteId;
    private int status;

    @JsonIgnore
    public static final int WAITING = 0;
    @JsonIgnore
    public static final int ACCEPT = 1;
    @JsonIgnore
    public static final int DENY = 2;

    public RequestMakeTrip() {
    }

    public RequestMakeTrip(long id, String senderId, String receiverId, long createdTime, long receiverRouteId, long driverRouteId, long passengerRouteId, int status) {
        this.id = id;
        this.senderId = senderId == null ? "" : senderId;
        this.receiverId = receiverId == null ? "" : receiverId;
        this.createdTime = createdTime;
        this.driverRouteId = driverRouteId;
        this.passengerRouteId = passengerRouteId;
        this.status = status;
    }

    public RequestMakeTrip(RequestMakeTrip that) {
        this.id = that.id;
        this.senderId = that.senderId == null ? "" : that.senderId;
        this.receiverId = that.receiverId == null ? "" : that.receiverId;
        this.createdTime = that.createdTime;
        this.driverRouteId = that.driverRouteId;
        this.passengerRouteId = that.passengerRouteId;
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

    public long getDriverRouteId() {
        return driverRouteId;
    }

    public void setDriverRouteId(long driverRouteId) {
        this.driverRouteId = driverRouteId;
    }

    public long getPassengerRouteId() {
        return passengerRouteId;
    }

    public void setPassengerRouteId(long passengerRouteId) {
        this.passengerRouteId = passengerRouteId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
