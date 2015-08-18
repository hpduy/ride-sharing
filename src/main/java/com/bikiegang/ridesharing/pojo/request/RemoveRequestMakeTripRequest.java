package com.bikiegang.ridesharing.pojo.request;

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
}
