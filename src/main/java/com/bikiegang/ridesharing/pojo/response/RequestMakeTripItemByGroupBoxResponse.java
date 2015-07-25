package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/23/15.
 */
public class RequestMakeTripItemByGroupBoxResponse {
    private long routeId;
    RequestMakeTripItemBoxResponse[] requestList;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public RequestMakeTripItemBoxResponse[] getRequestList() {
        return requestList;
    }

    public void setRequestList(RequestMakeTripItemBoxResponse[] requestList) {
        this.requestList = requestList;
    }
}
