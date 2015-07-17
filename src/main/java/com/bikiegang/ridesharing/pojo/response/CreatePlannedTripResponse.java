package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class CreatePlannedTripResponse {
    PlannedTripSortDetailResponse yourPlannedTrip;
    PlannedTripSortDetailResponse [] pairedPlannedTripsResult;

    public PlannedTripSortDetailResponse getYourPlannedTrip() {
        return yourPlannedTrip;
    }

    public void setYourPlannedTrip(PlannedTripSortDetailResponse yourPlannedTrip) {
        this.yourPlannedTrip = yourPlannedTrip;
    }

    public PlannedTripSortDetailResponse[] getPairedPlannedTripsResult() {
        return pairedPlannedTripsResult;
    }

    public void setPairedPlannedTripsResult(PlannedTripSortDetailResponse[] pairedPlannedTripsResult) {
        this.pairedPlannedTripsResult = pairedPlannedTripsResult;
    }
}
