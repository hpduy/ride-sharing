package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class PlannedTripDetailResponse extends PlannedTripSortDetailResponse {
    private String googleRoutingResult;

    public PlannedTripDetailResponse() {
    }

    public PlannedTripDetailResponse(PlannedTripSortDetailResponse that, String googleRoutingResult) {
        super(that);
        this.googleRoutingResult = googleRoutingResult;
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
