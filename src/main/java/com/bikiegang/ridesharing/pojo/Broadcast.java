package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Broadcast {
    private long id;
    private String userId;
    private String deviceId;
    private String regId;
    private int os;
    //final field
    @JsonIgnore
    public static final int ANDROID = 0;
    @JsonIgnore
    public static final int IOS = 1;
    @JsonIgnore
    public static final int WINDOW_PHONE = 2;

    public Broadcast() {
    }

    public Broadcast(long id, String userId, String deviceId, String regId, int os) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
        this.regId = regId;
        this.os = os;
    }
    public Broadcast(Broadcast that) {
        this.id = that.id;
        this.userId = that.userId;
        this.deviceId = that.deviceId;
        this.regId = that.regId;
        this.os = that.os;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }
}
