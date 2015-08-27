package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCurrentLocationWithPlannedTripRequest {

    private String currentPolyLine;
    private String userId;
    private long plannedTripId;

    public String getCurrentPolyLine() {
        return currentPolyLine;
    }

    public void setCurrentPolyLine(String currentPolyLine) {
        this.currentPolyLine = currentPolyLine;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPlannedTripId() {
        return plannedTripId;
    }

    public void setPlannedTripId(long plannedTripId) {
        this.plannedTripId = plannedTripId;
    }

    public UpdateCurrentLocationWithPlannedTripRequest() {
    }

    public UpdateCurrentLocationWithPlannedTripRequest(String currentPolyLine, String userId, long plannedTripId) {
        this.currentPolyLine = currentPolyLine;
        this.userId = userId;
        this.plannedTripId = plannedTripId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
