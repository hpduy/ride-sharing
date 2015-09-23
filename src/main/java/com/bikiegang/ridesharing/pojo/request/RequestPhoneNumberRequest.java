package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPhoneNumberRequest {

    private String senderId;
    private String receiverId;

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

    public RequestPhoneNumberRequest() {
    }

    public RequestPhoneNumberRequest(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    
    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }

}
