package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/25/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTripByCalendarResponse {
    private PlannedTripByDayResponse[] trips;

    public PlannedTripByDayResponse[] getTrips() {
        return trips;
    }

    public void setTrips(PlannedTripByDayResponse[] trips) {
        this.trips = trips;
    }
}
