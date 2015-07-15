package com.bikiegang.ridesharing.geocoding.GoogleRoutingObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutingResult {
    private GoogleRoute[] routes;
    private String status;

    public GoogleRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(GoogleRoute[] routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
