package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBroadcastRequest {

    private String userId;
    private String deviceId;
    private String regId;
    private int os;
    private int type;

    @JsonIgnore
    public static final int UPDATE = 1;
    @JsonIgnore
    public static final int DELETE = 2;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UpdateBroadcastRequest() {
    }

    public UpdateBroadcastRequest(String userId, String deviceId, String regId, int os, int type) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.regId = regId;
        this.os = os;
        this.type = type;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
