package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.logging.Logger;

/**
 * Created by hpduy17 on 8/25/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTripByCalendarRequest {

    private long startTime;
    private long endTIme;
    private String userId;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(long endTIme) {
        this.endTIme = endTIme;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetTripByCalendarRequest() {
    }

    public GetTripByCalendarRequest(long startTime, long endTIme, String userId) {
        this.startTime = startTime;
        this.endTIme = endTIme;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
