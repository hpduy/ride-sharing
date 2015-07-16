package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONObject;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoSearchParingRequest {
    private String creatorId;
    private JSONObject googleRoutingResult;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public JSONObject getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(JSONObject googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }
}
