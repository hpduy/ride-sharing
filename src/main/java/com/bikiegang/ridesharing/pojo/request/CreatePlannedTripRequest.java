package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePlannedTripRequest {

    private PlannedTripInfoRequest plannedTrip;
    private TripPattern[] patterns;

    public CreatePlannedTripRequest(PlannedTripInfoRequest plannedTrip, TripPattern[] patterns) {
        this.plannedTrip = plannedTrip;
        this.patterns = patterns;
    }

    public CreatePlannedTripRequest(String alphanumeric, int nextInt, long l, String randomAlphanumeric, boolean b, int i, String s, TripPattern[] patterns, LatLng[] waypoints) {
    }

    public CreatePlannedTripRequest(PlannedTripInfoRequest plannedTrip) {
        this.plannedTrip = plannedTrip;
    }

    public PlannedTripInfoRequest getPlannedTrip() {
        return plannedTrip;
    }

    public void setPlannedTrip(PlannedTripInfoRequest plannedTrip) {
        this.plannedTrip = plannedTrip;
    }

    public TripPattern[] getPatterns() {
        return patterns;
    }

    public void setPatterns(TripPattern[] patterns) {
        this.patterns = patterns;
    }
}
