package com.bikiegang.ridesharing.pojo.response;

import org.json.JSONObject;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class RouteDetailResponse extends RouteSortDetailResponse {
    private JSONObject googleRoutingResult;

    public RouteDetailResponse() {
    }

    public RouteDetailResponse(RouteSortDetailResponse that, JSONObject googleRoutingResult) {
        super(that);
        this.googleRoutingResult = googleRoutingResult;
    }

    public JSONObject getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(JSONObject googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
