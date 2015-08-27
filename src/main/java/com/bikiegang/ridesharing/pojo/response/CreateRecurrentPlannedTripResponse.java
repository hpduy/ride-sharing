package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class CreateRecurrentPlannedTripResponse {
    UserAndPlannedTripDetailByDayResponse[] pairedPlannedTripsByDayResult;

    public CreateRecurrentPlannedTripResponse() {
    }

    public UserAndPlannedTripDetailByDayResponse[] getPairedPlannedTripsByDayResult() {
        return pairedPlannedTripsByDayResult;
    }

    public void setPairedPlannedTripsByDayResult(UserAndPlannedTripDetailByDayResponse[] pairedPlannedTripsByDayResult) {
        this.pairedPlannedTripsByDayResult = pairedPlannedTripsByDayResult;
    }
}
