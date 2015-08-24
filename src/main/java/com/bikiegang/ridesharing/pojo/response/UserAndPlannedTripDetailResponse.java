package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserAndPlannedTripDetailResponse {
    private UserDetailResponse user;
    private PlannedTripDetailResponse plannedTrip;

    public UserAndPlannedTripDetailResponse() {
    }

    public UserAndPlannedTripDetailResponse(User user, PlannedTripDetailResponse plannedTrip) {
        this.user = new UserDetailResponse(user);
        this.plannedTrip = plannedTrip;
    }

    public PlannedTripDetailResponse getPlannedTrip() {
        return plannedTrip;
    }

    public void setPlannedTrip(PlannedTripDetailResponse plannedTrip) {
        this.plannedTrip = plannedTrip;
    }

    public UserDetailResponse getUser() {
        return user;
    }

    public void setUser(UserDetailResponse user) {
        this.user = user;
    }
}
