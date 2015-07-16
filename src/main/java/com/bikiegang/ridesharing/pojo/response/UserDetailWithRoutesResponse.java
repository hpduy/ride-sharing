package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class UserDetailWithRoutesResponse extends UserDetailResponse{
    private RouteSortDetailResponse[] routes;

    public UserDetailWithRoutesResponse(User user, RouteSortDetailResponse[] routes) {
        super(user);
        this.routes = routes;
    }

    public RouteSortDetailResponse[] getRoutes() {
        return routes;
    }

    public void setRoutes(RouteSortDetailResponse[] routes) {
        this.routes = routes;
    }
}
