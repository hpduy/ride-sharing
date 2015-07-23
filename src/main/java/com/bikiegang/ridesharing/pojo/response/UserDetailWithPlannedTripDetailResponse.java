package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithPlannedTripDetailResponse extends UserShortDetailResponse {
    private PlannedTripDetailResponse plannedTrip;

    public UserDetailWithPlannedTripDetailResponse() {
    }

    public UserDetailWithPlannedTripDetailResponse(User user, PlannedTripDetailResponse plannedTrip) {
        super(user);
        this.plannedTrip = plannedTrip;
    }

    public PlannedTripDetailResponse getPlannedTrip() {
        return plannedTrip;
    }

    public void setPlannedTrip(PlannedTripDetailResponse plannedTrip) {
        this.plannedTrip = plannedTrip;
    }
}
