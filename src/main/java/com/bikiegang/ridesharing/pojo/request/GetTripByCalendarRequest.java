package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
}
