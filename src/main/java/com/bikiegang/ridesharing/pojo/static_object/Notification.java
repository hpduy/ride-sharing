package com.bikiegang.ridesharing.pojo.static_object;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

/**
 * Created by hpduy17 on 9/3/15.
 */
public class Notification {
    private long createdTime = DateTimeUtil.now();
    private String senderName = "";
    private String senderId = "";

    public Notification(String senderName, String senderId) {
        this.senderName = senderName;
        this.senderId = senderId;
    }

    public Notification() {
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}