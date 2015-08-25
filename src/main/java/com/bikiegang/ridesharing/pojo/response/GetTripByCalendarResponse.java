package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 8/25/15.
 */
public class GetTripByCalendarResponse {
    private PlannedTripByDayResponse[] trips;

    public PlannedTripByDayResponse[] getTrips() {
        return trips;
    }

    public void setTrips(PlannedTripByDayResponse[] trips) {
        this.trips = trips;
    }
}
