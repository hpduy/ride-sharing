package com.bikiegang.ridesharing.pojo.response;

import org.json.JSONObject;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class PlannedTripDetailResponse extends PlannedTripShortDetailResponse {
    private String googleRoutingResult;

    public PlannedTripDetailResponse() {
    }

    public PlannedTripDetailResponse(PlannedTripShortDetailResponse that, JSONObject googleRoutingResult) {
        super(that);
        this.googleRoutingResult = googleRoutingResult.toString();
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
