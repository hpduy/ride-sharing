package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class CreatePlannedTripResponse {
    UserDetailWithPlannedTripDetailResponse yourPlannedTrip;
    UserDetailWithPlannedTripDetailResponse[] pairedPlannedTripsResult;

    public CreatePlannedTripResponse() {
    }

    public UserDetailWithPlannedTripDetailResponse getYourPlannedTrip() {
        return yourPlannedTrip;
    }

    public void setYourPlannedTrip(UserDetailWithPlannedTripDetailResponse yourPlannedTrip) {
        this.yourPlannedTrip = yourPlannedTrip;
    }

    public UserDetailWithPlannedTripDetailResponse[] getPairedPlannedTripsResult() {
        return pairedPlannedTripsResult;
    }

    public void setPairedPlannedTripsResult(UserDetailWithPlannedTripDetailResponse[] pairedPlannedTripsResult) {
        this.pairedPlannedTripsResult = pairedPlannedTripsResult;
    }
}
