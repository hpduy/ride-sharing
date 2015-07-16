package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class RouteDetailResponse extends RouteSortDetailResponse {
    private String googleRoutingResult;

    public RouteDetailResponse() {
    }

    public RouteDetailResponse(RouteSortDetailResponse that, String googleRoutingResult) {
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
