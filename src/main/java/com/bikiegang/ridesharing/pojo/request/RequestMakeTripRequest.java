package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMakeTripRequest {
    private String senderId = "";
    private String receiverId = "";
    private long senderRouteId;
    private long receiverRouteId;
    private int senderRole;

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

    public long getReceiverRouteId() {
        return receiverRouteId;
    }

    public void setReceiverRouteId(long receiverRouteId) {
        this.receiverRouteId = receiverRouteId;
    }

    public long getSenderRouteId() {
        return senderRouteId;
    }

    public void setSenderRouteId(long senderRouteId) {
        this.senderRouteId = senderRouteId;
    }

    public int getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(int senderRole) {
        this.senderRole = senderRole;
    }
}
