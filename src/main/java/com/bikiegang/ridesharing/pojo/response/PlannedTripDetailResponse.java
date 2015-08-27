package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.Route;

import java.io.IOException;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class PlannedTripDetailResponse extends PlannedTripShortDetailResponse {
    private String googleRoutingResult;

    public PlannedTripDetailResponse() {
    }

    public PlannedTripDetailResponse(PlannedTrip that, String senderId) throws IOException {
        super(that, senderId);
        Route route  = Database.getInstance().getRouteHashMap().get(that.getRouteId());
        this.googleRoutingResult = route.getRawRoutingResult().toString();
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
