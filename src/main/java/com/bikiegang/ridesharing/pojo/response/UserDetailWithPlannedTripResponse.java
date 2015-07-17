package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithPlannedTripResponse extends UserDetailResponse{
    private PlannedTripSortDetailResponse[] plannedTrips;

    public UserDetailWithPlannedTripResponse(User user, PlannedTripSortDetailResponse[] plannedTrips) {
        super(user);
        this.plannedTrips = plannedTrips;
    }

    public PlannedTripSortDetailResponse[] getPlannedTrips() {
        return plannedTrips;
    }

    public void setPlannedTrips(PlannedTripSortDetailResponse[] plannedTrips) {
        this.plannedTrips = plannedTrips;
    }
}
