package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCurrentLocationRequest {
    private String currentPolyLine;
    private String userId;

    public String getCurrentPolyLine() {
        return currentPolyLine;
    }

    public void setCurrentPolyLine(String currentPolyLine) {
        this.currentPolyLine = currentPolyLine;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
