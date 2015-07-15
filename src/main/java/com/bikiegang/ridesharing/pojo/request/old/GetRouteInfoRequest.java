package com.bikiegang.ridesharing.pojo.request.old;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/29/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRouteInfoRequest {
    private long routeId;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }
}
