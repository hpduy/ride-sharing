package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;

/**
 * Created by hpduy17 on 7/23/15.
 */

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
