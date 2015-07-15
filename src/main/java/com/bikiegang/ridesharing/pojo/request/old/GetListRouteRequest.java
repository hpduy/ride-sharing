package com.bikiegang.ridesharing.pojo.request.old;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/29/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListRouteRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
