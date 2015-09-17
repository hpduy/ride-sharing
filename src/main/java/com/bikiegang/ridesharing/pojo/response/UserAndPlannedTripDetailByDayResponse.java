package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAndPlannedTripDetailByDayResponse {
    private long epochDay;
    private FeedResponse yourPlannedTrip;
    private FeedResponse[] pairedPlannedTripsResult;

    public UserAndPlannedTripDetailByDayResponse() {
    }

    public UserAndPlannedTripDetailByDayResponse(long epochDay,FeedResponse yourPlannedTrip, FeedResponse[] pairedPlannedTripsResult) {
        this.yourPlannedTrip = yourPlannedTrip;
        this.epochDay = epochDay;
        this.pairedPlannedTripsResult = pairedPlannedTripsResult;
    }

    public long getEpochDay() {
        return epochDay;
    }

    public void setEpochDay(long epochDay) {
        this.epochDay = epochDay;
    }

    public FeedResponse getYourPlannedTrip() {
        return yourPlannedTrip;
    }

    public void setYourPlannedTrip(FeedResponse yourPlannedTrip) {
        this.yourPlannedTrip = yourPlannedTrip;
    }

    public FeedResponse[] getPairedPlannedTripsResult() {
        return pairedPlannedTripsResult;
    }

    public void setPairedPlannedTripsResult(FeedResponse[] pairedPlannedTripsResult) {
        this.pairedPlannedTripsResult = pairedPlannedTripsResult;
    }
}
