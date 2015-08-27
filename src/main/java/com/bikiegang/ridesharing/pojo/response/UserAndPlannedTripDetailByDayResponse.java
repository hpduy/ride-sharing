package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserAndPlannedTripDetailByDayResponse {
    private long epochDay;
    private UserAndPlannedTripDetailResponse yourPlannedTrip;
    private UserAndPlannedTripDetailResponse[] pairedPlannedTripsResult;

    public UserAndPlannedTripDetailByDayResponse() {
    }

    public UserAndPlannedTripDetailByDayResponse(long epochDay,UserAndPlannedTripDetailResponse yourPlannedTrip, UserAndPlannedTripDetailResponse[] pairedPlannedTripsResult) {
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

    public UserAndPlannedTripDetailResponse[] getPairedPlannedTripsResult() {
        return pairedPlannedTripsResult;
    }

    public void setPairedPlannedTripsResult(UserAndPlannedTripDetailResponse[] pairedPlannedTripsResult) {
        this.pairedPlannedTripsResult = pairedPlannedTripsResult;
    }

    public UserAndPlannedTripDetailResponse getYourPlannedTrip() {
        return yourPlannedTrip;
    }

    public void setYourPlannedTrip(UserAndPlannedTripDetailResponse yourPlannedTrip) {
        this.yourPlannedTrip = yourPlannedTrip;
    }
}
