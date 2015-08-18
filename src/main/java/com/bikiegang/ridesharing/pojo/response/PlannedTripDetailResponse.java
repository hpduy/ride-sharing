package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.PlannedTrip;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class PlannedTripDetailResponse extends PlannedTripShortDetailResponse {
    private String googleRoutingResult;

    public PlannedTripDetailResponse() {
    }

    public PlannedTripDetailResponse(PlannedTrip that, String senderId, JSONObject googleRoutingResult) throws IOException {
        super(that, senderId);
        this.googleRoutingResult = googleRoutingResult.toString();
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
