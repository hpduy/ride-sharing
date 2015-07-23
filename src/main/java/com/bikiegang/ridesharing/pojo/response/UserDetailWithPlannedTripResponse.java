package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithPlannedTripResponse extends UserDetailResponse{
    private PlannedTripShortDetailResponse[] plannedTrips;

    public UserDetailWithPlannedTripResponse(User user, PlannedTripShortDetailResponse[] plannedTrips) {
        super(user);
        this.plannedTrips = plannedTrips;
    }

    public PlannedTripShortDetailResponse[] getPlannedTrips() {
        return plannedTrips;
    }

    public void setPlannedTrips(PlannedTripShortDetailResponse[] plannedTrips) {
        this.plannedTrips = plannedTrips;
    }
}
