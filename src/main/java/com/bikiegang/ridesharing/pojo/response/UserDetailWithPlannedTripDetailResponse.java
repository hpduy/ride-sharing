package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithPlannedTripDetailResponse {
    private UserDetailResponse user;
    private PlannedTripDetailResponse plannedTrip;

    public UserDetailWithPlannedTripDetailResponse() {
    }

    public UserDetailWithPlannedTripDetailResponse(User user, PlannedTripDetailResponse plannedTrip) {
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
