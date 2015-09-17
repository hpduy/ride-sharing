package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.Route;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlannedTripDetailResponse extends PlannedTripShortDetailResponse {
    private String googleRoutingResult;
    private LatLng[] waypoints;
    public PlannedTripDetailResponse() {
    }

    public PlannedTripDetailResponse(PlannedTrip that, String senderId) throws IOException {
        super(that, senderId);
        Route route  = Database.getInstance().getRouteHashMap().get(that.getRouteId());
        this.googleRoutingResult = route.getRawRoutingResult().toString();
        this.waypoints = route.getWaypoints();
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }

    public LatLng[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(LatLng[] waypoints) {
        this.waypoints = waypoints;
    }
}
