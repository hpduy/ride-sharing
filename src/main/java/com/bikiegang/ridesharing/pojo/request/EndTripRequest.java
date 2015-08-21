package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EndTripRequest {
    private long tripId;
    private LatLng endLocation;
    private String tripTrailPolyLine;

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public String getTripTrailPolyLine() {
        return tripTrailPolyLine;
    }

    public void setTripTrailPolyLine(String tripTrailPolyLine) {
        this.tripTrailPolyLine = tripTrailPolyLine;
    }
}
