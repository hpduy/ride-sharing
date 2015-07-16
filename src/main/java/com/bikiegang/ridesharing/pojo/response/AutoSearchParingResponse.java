package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class AutoSearchParingResponse extends RouteSortDetailResponse {
    UserDetailWithRouteDetailResponse[] drivers;
    UserDetailWithRouteDetailResponse[] passenger;

    public UserDetailWithRouteDetailResponse[] getDrivers() {
        return drivers;
    }

    public void setDrivers(UserDetailWithRouteDetailResponse[] drivers) {
        this.drivers = drivers;
    }

    public UserDetailWithRouteDetailResponse[] getPassenger() {
        return passenger;
    }

    public void setPassenger(UserDetailWithRouteDetailResponse[] passenger) {
        this.passenger = passenger;
    }
}
