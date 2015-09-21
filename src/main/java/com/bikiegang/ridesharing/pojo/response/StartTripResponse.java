package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/13/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StartTripResponse {
    long tripId;

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public StartTripResponse(long tripId) {
        this.tripId = tripId;
    }

    public StartTripResponse() {
    }
}
