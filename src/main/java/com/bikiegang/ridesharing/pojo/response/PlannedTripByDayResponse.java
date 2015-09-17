package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlannedTripByDayResponse {
    private long epochDay;
    private PlannedTripDetailResponse[] plannedTrips;

    public PlannedTripByDayResponse() {
    }


    public PlannedTripByDayResponse(long epochDay, PlannedTripDetailResponse[] plannedTrips) {
        this.epochDay = epochDay;
        this.plannedTrips = plannedTrips;
    }

    public long getEpochDay() {
        return epochDay;
    }

    public void setEpochDay(long epochDay) {
        this.epochDay = epochDay;
    }

    public PlannedTripDetailResponse[] getPlannedTrips() {
        return plannedTrips;
    }

    public void setPlannedTrips(PlannedTripDetailResponse[] plannedTrips) {
        this.plannedTrips = plannedTrips;
    }
}
