package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/25/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTripByCalendarRequest {

    private long startTime;
    private long endTime;
    private String userId;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetTripByCalendarRequest() {
    }

    public GetTripByCalendarRequest(long startTime, long endTime, String userId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
