package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
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

    public GetPlannedTripDetailRequest() {
    }

    public GetPlannedTripDetailRequest(long plannedTripId, String userId) {
        this.plannedTripId = plannedTripId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
