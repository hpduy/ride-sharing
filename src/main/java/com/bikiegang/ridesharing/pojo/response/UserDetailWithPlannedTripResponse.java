package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithPlannedTripResponse extends UserDetailResponse{
    private PlannedTripDetailResponse[] plannedTrips;

    public UserDetailWithPlannedTripResponse(User user, PlannedTripDetailResponse[] plannedTrips) {
        super(user);
        this.plannedTrips = plannedTrips;
    }

    public PlannedTripDetailResponse[] getPlannedTrips() {
        return plannedTrips;
    }

    public void setPlannedTrips(PlannedTripDetailResponse[] plannedTrips) {
        this.plannedTrips = plannedTrips;
    }
}
