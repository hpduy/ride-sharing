package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPlannedTripDetailRequest {
    private long plannedTripId;
    private String userId;
    public long getPlannedTripId() {
        return plannedTripId;
    }

    public void setPlannedTripId(long plannedTripId) {
        this.plannedTripId = plannedTripId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
