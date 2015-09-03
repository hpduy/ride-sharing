package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/23/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveRequestMakeTripRequest {
    private String userId;
    private long plannedTripId;

    public String getUserId() {
        return userId;
    }

    public long getPlannedTripId() {
        return plannedTripId;
    }

    public RemoveRequestMakeTripRequest() {
    }

    public RemoveRequestMakeTripRequest(String userId, long plannedTripId) {
        this.userId = userId;
        this.plannedTripId = plannedTripId;
    }
     @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
    
}
