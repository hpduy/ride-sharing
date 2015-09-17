package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSingleFuturePlannedTripResponse {
    FeedResponse yourPlannedTrip;
    FeedResponse[] pairedPlannedTripsResult;

    public CreateSingleFuturePlannedTripResponse() {
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
