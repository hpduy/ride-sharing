package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithRouteDetailResponse extends UserSortDetailResponse{
    private RouteDetailResponse route;

    public UserDetailWithRouteDetailResponse(User user, RouteDetailResponse route) {
        super(user);
        this.route = route;
    }

    public RouteDetailResponse getRoute() {
        return route;
    }

    public void setRoute(RouteDetailResponse route) {
        this.route = route;
    }
}
