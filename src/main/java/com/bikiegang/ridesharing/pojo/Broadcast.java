package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Broadcast {
    private String id = ""; // <deviceId#userId>
    private String userId = "";
    private String deviceId = "";
    private String regId;
    private int os;

    public Broadcast() {
    }

    public Broadcast(String id, String userId, String deviceId, String regId, int os) {
        this.id = id == null ? "" : id;
        this.userId = userId == null ? "" : userId;
        this.deviceId = deviceId == null ? "" : deviceId;
        this.regId = regId == null ? "" : regId;
        this.os = os;
    }

    public Broadcast(Broadcast that) {
        this.id = that.id == null ? "" : that.id;
        this.userId = that.userId == null ? "" : that.userId;
        this.deviceId = that.deviceId == null ? "" : that.deviceId;
        this.regId = that.regId == null ? "" : that.regId;
        this.os = that.os;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
