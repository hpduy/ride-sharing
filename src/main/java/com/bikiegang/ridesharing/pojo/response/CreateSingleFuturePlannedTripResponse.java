package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class CreateSingleFuturePlannedTripResponse {
    UserAndPlannedTripDetailResponse yourPlannedTrip;
    UserAndPlannedTripDetailResponse[] pairedPlannedTripsResult;

    public CreateSingleFuturePlannedTripResponse() {
    }

    public UserAndPlannedTripDetailResponse getYourPlannedTrip() {
        return yourPlannedTrip;
    }

    public void setYourPlannedTrip(UserAndPlannedTripDetailResponse yourPlannedTrip) {
        this.yourPlannedTrip = yourPlannedTrip;
    }

    public UserAndPlannedTripDetailResponse[] getPairedPlannedTripsResult() {
        return pairedPlannedTripsResult;
    }

    public void setPairedPlannedTripsResult(UserAndPlannedTripDetailResponse[] pairedPlannedTripsResult) {
        this.pairedPlannedTripsResult = pairedPlannedTripsResult;
    }
}
