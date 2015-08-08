package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithPlannedTripResponse{
    private UserDetailResponse user;
    private PlannedTripDetailResponse[] plannedTrips;

    public UserDetailWithPlannedTripResponse(User user, PlannedTripDetailResponse[] plannedTrips) {
        this.user = new UserDetailResponse(user);
        this.plannedTrips = plannedTrips;
    }

    public PlannedTripDetailResponse[] getPlannedTrips() {
        return plannedTrips;
    }

    public void setPlannedTrips(PlannedTripDetailResponse[] plannedTrips) {
        this.plannedTrips = plannedTrips;
    }

    public UserDetailResponse getUser() {
        return user;
    }

    public void setUser(UserDetailResponse user) {
        this.user = user;
    }
}
