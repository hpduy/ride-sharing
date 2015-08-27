package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMakeTripRequest {

    private String senderId;
    private String receiverId ;
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

    public RequestMakeTripRequest() {
    }

    public RequestMakeTripRequest(String senderId, String receiverId, long senderPlannedTripId, long receiverPlannedTripId, double price, String googleRoutingResult) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderPlannedTripId = senderPlannedTripId;
        this.receiverPlannedTripId = receiverPlannedTripId;
        this.price = price;
        this.googleRoutingResult = googleRoutingResult;
    }

  
    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
