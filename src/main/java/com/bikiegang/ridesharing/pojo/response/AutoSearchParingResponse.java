package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class AutoSearchParingResponse {
    UserDetailWithPlannedTripDetailResponse[] drivers;
    UserDetailWithPlannedTripDetailResponse[] passengers;

    public UserDetailWithPlannedTripDetailResponse[] getDrivers() {
        return drivers;
    }

    public void setDrivers(UserDetailWithPlannedTripDetailResponse[] drivers) {
        this.drivers = drivers;
    }

    public UserDetailWithPlannedTripDetailResponse[] getPassengers() {
        return passengers;
    }

    public void setPassengers(UserDetailWithPlannedTripDetailResponse[] passengers) {
        this.passengers = passengers;
    }
}
