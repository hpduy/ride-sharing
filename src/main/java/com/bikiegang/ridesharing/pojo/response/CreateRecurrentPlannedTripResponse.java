package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class CreateRecurrentPlannedTripResponse {
    UserAndPlannedTripDetailResponse yourPlannedTrip;
    UserAndPlannedTripDetailByDayResponse[] pairedPlannedTripsByDayResult;

    public CreateRecurrentPlannedTripResponse() {
    }

    public UserAndPlannedTripDetailResponse getYourPlannedTrip() {
        return yourPlannedTrip;
    }

    public void setYourPlannedTrip(UserAndPlannedTripDetailResponse yourPlannedTrip) {
        this.yourPlannedTrip = yourPlannedTrip;
    }

    public UserAndPlannedTripDetailByDayResponse[] getPairedPlannedTripsByDayResult() {
        return pairedPlannedTripsByDayResult;
    }

    public void setPairedPlannedTripsByDayResult(UserAndPlannedTripDetailByDayResponse[] pairedPlannedTripsByDayResult) {
        this.pairedPlannedTripsByDayResult = pairedPlannedTripsByDayResult;
    }
}
