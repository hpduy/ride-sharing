package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Created by hpduy17 on 6/30/15.
 */
public class RequestMakeTrip implements PojoBase {
    private long id;
    private String senderId = "";
    private String receiverId = "";
    private int senderRole;
    private long createdTime;
    private long driverPlannedTripId;
    private long passengerPlannedTripId;
    private int status;
    private double price;

    @JsonIgnore
    public static final int WAITING = 0;
    @JsonIgnore
    public static final int ACCEPT = 1;
    @JsonIgnore
    public static final int DENY = 2;

    public RequestMakeTrip() {
    }

    public RequestMakeTrip(long id, String senderId, String receiverId, long createdTime, long receiverRouteId, long driverPlannedTripId, long passengerPlannedTripId, int senderRole,int status) {
        this.id = id;
        this.senderId = senderId == null ? "" : senderId;
        this.receiverId = receiverId == null ? "" : receiverId;
        this.createdTime = createdTime;
        this.driverPlannedTripId = driverPlannedTripId;
        this.passengerPlannedTripId = passengerPlannedTripId;
        this.senderRole = senderRole;
        this.status = status;
    }

    public RequestMakeTrip(RequestMakeTrip that) {
        this.id = that.id;
        this.senderId = that.senderId == null ? "" : that.senderId;
        this.receiverId = that.receiverId == null ? "" : that.receiverId;
        this.createdTime = that.createdTime;
        this.driverPlannedTripId = that.driverPlannedTripId;
        this.passengerPlannedTripId = that.passengerPlannedTripId;
        this.senderRole = that.senderRole;
        this.status = that.status;
        this.price = that.price;
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

    public long getDriverPlannedTripId() {
        return driverPlannedTripId;
    }

    public void setDriverPlannedTripId(long driverPlannedTripId) {
        this.driverPlannedTripId = driverPlannedTripId;
    }

    public long getPassengerPlannedTripId() {
        return passengerPlannedTripId;
    }

    public void setPassengerPlannedTripId(long passengerPlannedTripId) {
        this.passengerPlannedTripId = passengerPlannedTripId;
    }

    public int getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(int senderRole) {
        this.senderRole = senderRole;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/**
 * Change log
 * them field price
 */
