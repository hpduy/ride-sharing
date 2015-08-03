package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMakeTripRequest {
    private String senderId = "";
    private String receiverId = "";
    private long senderPlannedTripId;
    private long receiverPlannedTripId;
    private double price;
    private String googleRoutingResult;

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

    public long getReceiverPlannedTripId() {
        return receiverPlannedTripId;
    }

    public void setReceiverPlannedTripId(long receiverPlannedTripId) {
        this.receiverPlannedTripId = receiverPlannedTripId;
    }

    public long getSenderPlannedTripId() {
        return senderPlannedTripId;
    }

    public void setSenderPlannedTripId(long senderPlannedTripId) {
        this.senderPlannedTripId = senderPlannedTripId;
    }


    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

