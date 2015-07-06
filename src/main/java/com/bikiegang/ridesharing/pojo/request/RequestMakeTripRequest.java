package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/1/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMakeTripRequest {
    private String senderId = "";
    private String receiverId = "";
    private long receiverRouteId;

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
}
